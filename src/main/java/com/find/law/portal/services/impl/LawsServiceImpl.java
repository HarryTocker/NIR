package com.find.law.portal.services.impl;

import com.find.law.portal.controllers.dto.generic.LawsByTypeDto;
import com.find.law.portal.core.content.categories.CrimeCategoryComparisonType;
import com.find.law.portal.core.content.categories.CrimeCategoryData;
import com.find.law.portal.core.content.categories.CrimeCategoryType;
import com.find.law.portal.core.content.laws.*;
import com.find.law.portal.core.content.punishments.PunishmentType;
import com.find.law.portal.core.utils.pair.Pair;
import com.find.law.portal.core.utils.tuple.Tuple;
import com.find.law.portal.mappers.GenericBeanMapper;
import com.find.law.portal.repositories.CrimeCategoryRepository;
import com.find.law.portal.repositories.LawRepository;
import com.find.law.portal.repositories.entities.categories.CrimeCategoryEntity;
import com.find.law.portal.repositories.entities.laws.LawEntity;
import com.find.law.portal.services.LawsService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.*;

/**
 * Реализация сервиса для работы с законами.
 */
@Service
public class LawsServiceImpl implements LawsService {
    private final LawRepository lawRepository;
    private final CrimeCategoryRepository crimeCategoryRepository;
    private final GenericBeanMapper beanMapper;

    public LawsServiceImpl(
            LawRepository lawRepository,
            CrimeCategoryRepository crimeCategoryRepository,
            GenericBeanMapper beanMapper
    ) {
        this.lawRepository = lawRepository;
        this.crimeCategoryRepository = crimeCategoryRepository;
        this.beanMapper = beanMapper;
    }

    @Override
    public LawsByTypeDto getLawsByType() {
        Collection<LawEntity> laws = lawRepository.findAll();

        List<String> intentional = new LinkedList<>();
        List<String> negligence = new LinkedList<>();
        List<String> unknown = new LinkedList<>();

        for(LawEntity law : laws) {
            LawType lawType = LawType.valueOf(law.getType());
            switch (lawType) {
                case INTENTIONAL -> intentional.add(law.getArticle());
                case NEGLIGENCE -> negligence.add(law.getArticle());
                case UNKNOWN -> unknown.add(law.getArticle());
            }
        }

        return new LawsByTypeDto(negligence, intentional, unknown);
    }

    @Override
    public void updateLawsType(LawsByTypeDto lawsType) {
        Set<String> intentional = new HashSet<>(lawsType.intentional());
        Set<String> negligence = new HashSet<>(lawsType.negligence());

        Collection<LawEntity> laws = lawRepository.findAll();

        for(LawEntity law : laws) {
            String article = law.getArticle();
            if (intentional.contains(article)) {
                law.setType(LawType.INTENTIONAL.toString());
            } else if (negligence.contains(article)) {
                law.setType(LawType.NEGLIGENCE.toString());
            } else {
                law.setType(LawType.UNKNOWN.toString());
            }
        }

        lawRepository.saveAll(laws);
    }

    @Override
    public LawData findLaw(String article, String text) {
        // Если указана статья закона, например 105, выполняем поиск только по ней.
        if (!StringUtils.isEmptyOrWhitespace(article)) {
            var law = lawRepository.findById(article).orElseThrow(() -> new EntityNotFoundException("Law with specified id [%s] not found".formatted(article)));
            return beanMapper.map(law, LawData.class);
        }

        if (StringUtils.isEmptyOrWhitespace(text)) {
            throw new EntityNotFoundException("Cannot search because text is empty");
        }

        // Если статья не указана, ищем по тексту. Пробелы в тексте заменяются на символ '%'.
        // Это сделано для того, чтобы при указанном тексте "Причинение вреда здоровью" в базе данных были найдены результаты "Умышленное причинение тяжкого вреда здоровью".
        String query = text.replaceAll("\s+", " ").replaceAll("\s", "%");
        var law = lawRepository.searchAllByNameLike(query).stream()
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Could not find laws for the specified text"));
        return beanMapper.map(law, LawData.class);
    }

    @Override
    public Pair<LawData, Collection<Tuple<LawPartData, LawPartPunishData, Pair<CrimeCategoryData, CrimeCategoryData>>>> findLawWithMaxPunishment(String article, String text) {
        LawData law = findLaw(article, text);
        Collection<CrimeCategoryData> crimeCategories = beanMapper.map(crimeCategoryRepository.findAll(), CrimeCategoryData.class)
                .stream().sorted(Comparator.comparing(CrimeCategoryData::getCategoryType))
                .toList();

        List<Tuple<LawPartData, LawPartPunishData, Pair<CrimeCategoryData, CrimeCategoryData>>> maxPunishments = new ArrayList<>(law.getParts().size());

        for(LawPartData lawPart : law.getParts()) {
            LawPartPunishData max = lawPart.getPunishments().stream().findFirst().orElseThrow();
            if (lawPart.getPunishments().size() > 1) {
                for (LawPartPunishData lawPartPunish : lawPart.getPunishments()) {
                    if (lawPartPunish.isLifeTime()) {
                        max = lawPartPunish;
                        break;
                    }

                    int compareResult = lawPartPunish.getType().compareTo(max.getType());
                    if (compareResult > 0) {
                        max = lawPartPunish;
                    } else if (compareResult == 0 && lawPartPunish.getMax().getNumber() > max.getMax().getNumber()) {
                        max = lawPartPunish;
                    }
                }
            }

            CrimeCategoryData possibleCategory = crimeCategories.stream().findFirst().orElseThrow();
            CrimeCategoryData optionalCategory = law.getType() == LawType.UNKNOWN ? possibleCategory : null;
            for (var crimeCategory : crimeCategories) {
                if (max.isLifeTime()) {
                    if (crimeCategory.getCategoryType() != CrimeCategoryType.PARTICULARLY_SERIOUS) {
                        continue;
                    } else {
                        possibleCategory = crimeCategory;
                        if (law.getType() == LawType.UNKNOWN) {
                            optionalCategory = possibleCategory;
                        }
                        break;
                    }
                }

                if (law.getType() == LawType.UNKNOWN) {
                    if (crimeCategory.getLawType() == LawType.INTENTIONAL) {
                        if (isValidCategory(crimeCategory, max)) {
                            possibleCategory = crimeCategory;
                        }
                    } else {
                        if (isValidCategory(crimeCategory, max)) {
                            optionalCategory = crimeCategory;
                        }
                    }
                } else {
                    if (crimeCategory.getLawType() != law.getType()) {
                        continue;
                    }

                    if (isValidCategory(crimeCategory, max)) {
                        possibleCategory = crimeCategory;
                    }
                }
            }

            maxPunishments.add(Tuple.of(lawPart, max, Pair.of(possibleCategory, optionalCategory)));
        }

        return Pair.of(law, maxPunishments);
    }

    private static boolean isValidCategory(CrimeCategoryData crimeCategory, LawPartPunishData max) {
        if (max.getMax().getType() != PunishmentType.YEARS) {
            return crimeCategory.getCategoryType() == CrimeCategoryType.MINOR;
        }

        return crimeCategory.getComparisonType() == CrimeCategoryComparisonType.LESS
                ? max.getMax().getNumber() <= crimeCategory.getPunishment().getNumber()
                : max.getMax().getNumber() >= crimeCategory.getPunishment().getNumber();
    }
}
