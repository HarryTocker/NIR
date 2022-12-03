package com.find.law.portal.services.impl;

import com.find.law.portal.repositories.LawRepository;
import com.find.law.portal.repositories.entities.LawEntity;
import com.find.law.portal.services.LawsService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.List;

/**
 * Реализация сервиса для работы с законами.
 */
@Service
public class LawsServiceImpl implements LawsService {
    private final LawRepository lawRepository;

    public LawsServiceImpl(LawRepository lawRepository) {
        this.lawRepository = lawRepository;
    }

    @Override
    public LawEntity findLaw(String article, String text) {
        // Если указана статья закона, например 105, выполняем поиск только по ней.
        if (article != null && !StringUtils.isEmptyOrWhitespace(article)) {
            return lawRepository.findById(article).orElseThrow(() -> new EntityNotFoundException("Law with specified id [%s] not found".formatted(article)));
        }

        if (text == null || StringUtils.isEmptyOrWhitespace(text)) {
            throw new EntityNotFoundException("Cannot search because text is empty");
        }

        // Если статья не указана, ищем по тексту. Пробелы в тексте заменяются на символ '%'.
        // Это сделано для того, чтобы при указанном тексте "Причинение вреда здоровью" в базе данных были найдены результаты "Умышленное причинение тяжкого вреда здоровью".
        String search = text.replaceAll("\s+", " ").replaceAll("\s", "%");
        List<LawEntity> laws = lawRepository.searchAllByNameLike(search);
        if (laws.size() == 0) {
            throw new EntityNotFoundException("Could not find laws for the specified text");
        }

        return laws.get(0);
    }
}
