package com.find.law.portal.controllers;

import com.find.law.portal.controllers.dto.ErrorResponseDto;
import com.find.law.portal.controllers.dto.LawDataDto;
import com.find.law.portal.law.comparators.LawPartPunishmentComparator;
import com.find.law.portal.mappers.LawDataMapper;
import com.find.law.portal.repositories.entities.LawEntity;
import com.find.law.portal.services.LawsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Контроллер API запросов, касающихся законов.
 */
@RestController
@RequestMapping("api/laws")
public class LawsController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String articleRegex = "(\\d{3}\\.\\d+)|(\\d{3})";

    private static final String partRegex = "(пункт \\d+)|(часть \\d+)";

    private final Pattern articlePattern = Pattern.compile(articleRegex);

    private final Pattern partPattern = Pattern.compile(partRegex);

    private final LawsService lawsService;

    private final LawDataMapper lawDataMapper;

    public LawsController(LawsService lawsService, LawDataMapper lawDataMapper) {
        this.lawsService = lawsService;
        this.lawDataMapper = lawDataMapper;
    }

    /**
     * Найти максимальное наказание для указанного закона/статьи закона.
     *
     * @param searchText текст для поиска. Может содержать номер закона, статью закона или наименование закона. Примеры: "105", "105 часть 2", "Убийство".
     * @return LawDataDto - успешно. ErrorResponse - при возникновении ошибки.
     */
    @GetMapping(value = "/find/max_punishment/{searchText}", produces = "application/json")
    public ResponseEntity<?> findMaxPunishmentForLaw(@PathVariable String searchText) {
        if (searchText == null || StringUtils.isEmptyOrWhitespace(searchText)) {
            return ResponseEntity.badRequest().body(new ErrorResponseDto(0, "Невозможно выполнить поиск по пустой строке", null));
        }

        try {
            // Получаем статью закона или текст. Из текста исключаются все виды статьи, если имеются, и лишние слова.
            // Например, поиск по тексту: "УК РФ Убийство часть 2" будет искать по тексту "Убийство".
            String article = null;
            String text = searchText.replaceAll(articleRegex, "").replaceAll(partRegex, "").replaceAll("УК", "").replaceAll("РФ", "");
            Matcher articleMatcher = articlePattern.matcher(searchText);
            if (articleMatcher.find()) {
                article = articleMatcher.group();
            }

            LawEntity law = lawsService.findLaw(article, text);

            LawDataDto response = lawDataMapper.map(law);

            // Если в законе больше одной части нужно проверить есть ли в запросе указание конкретной части.
            if (response.getParts().size() != 1) {
                // Пытаемся найти часть. Например, поиск по тексту "УК РФ 105 часть 2" возьмет первый результат, который начинается на "2".
                Matcher partMatcher = partPattern.matcher(searchText);
                final String partText = partMatcher.find() ? partMatcher.group().replaceAll("\\D+", "") : null;

                if (partText != null) {
                    response.setParts(Collections.singleton(response.getParts().stream().filter(part -> part.getPart().startsWith(partText)).findFirst().orElseThrow()));
                }
            }

            // Получаем максимальное наказание для каждой части.
            response.getParts().forEach(part -> part.setPunishments(Collections.singleton(part.getPunishments().stream().max(new LawPartPunishmentComparator()).orElseThrow())));

            return ResponseEntity.ok(response);
        } catch (Exception cause) {
            logger.error("Failed to get law data: {}", cause.getMessage());
            return ResponseEntity.status(404).body(new ErrorResponseDto(1, "Не удалось найти указанный закон", cause.getMessage()));
        }
    }
}
