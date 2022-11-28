package com.find.law.portal.services.impl;

import com.find.law.portal.law.LawComparator;
import com.find.law.portal.repositories.LawRepository;
import com.find.law.portal.repositories.entities.LawEntity;
import com.find.law.portal.repositories.entities.LawPartEntity;
import com.find.law.portal.repositories.entities.LawPartPunishEntity;
import com.find.law.portal.services.LawsService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@Service
public class LawsServiceImpl implements LawsService {
    private final LawRepository lawRepository;

    private final LawComparator lawComparator;

    public LawsServiceImpl(LawRepository lawRepository) {
        this.lawRepository = lawRepository;
        this.lawComparator = new LawComparator();
    }

    @Override
    public LawEntity findLaw(String article, String text) {
        if (article != null && !StringUtils.isEmptyOrWhitespace(article)) {
            return lawRepository.findById(article).orElseThrow(() -> new EntityNotFoundException("Law with specified id [%s] not found".formatted(article)));
        }

        if (text == null || StringUtils.isEmptyOrWhitespace(text)) {
            throw new EntityNotFoundException("Cannot search because text is empty");
        }

        String search = text.replaceAll("\s+", " ").replaceAll("\s", "%");
        List<LawEntity> laws = lawRepository.searchAllByNameLike(search);
        if (laws.size() == 0) {
            throw new EntityNotFoundException("Could not find laws for the specified text");
        }

        return laws.get(0);
    }

    @Override
    public LawPartPunishEntity findMaxPunishment(LawPartEntity part) {
        List<LawPartPunishEntity> punishments = part.getPunishments();
        if (punishments.size() == 1) {
            return punishments.get(0);
        }

        LawPartPunishEntity current = punishments.get(0);
        for (int i = 1; i < punishments.size(); i++) {
            LawPartPunishEntity other = punishments.get(i);
            if (lawComparator.comparePunishments(current, other)) {
                current = other;
            }
        }

        return current;
    }
}
