package com.find.law.portal.controllers;

import com.find.law.portal.controllers.dto.errors.ErrorResponseDto;
import com.find.law.portal.controllers.dto.generic.LawPartWithCrimeCategoryDto;
import com.find.law.portal.controllers.dto.generic.LawWithCrimeCategoryDto;
import com.find.law.portal.controllers.dto.generic.LawsByTypeDto;
import com.find.law.portal.core.content.categories.CrimeCategoryData;
import com.find.law.portal.core.content.laws.*;
import com.find.law.portal.core.utils.pair.Pair;
import com.find.law.portal.core.utils.tuple.Tuple;
import com.find.law.portal.localization.LocalizationService;
import com.find.law.portal.services.LawsService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.Collection;
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

    private final LocalizationService localizationService;

    public LawsController(LawsService lawsService, LocalizationService localizationService) {
        this.lawsService = lawsService;
        this.localizationService = localizationService;
    }

    @GetMapping(value = "/types", produces = "application/json")
    public ResponseEntity<LawsByTypeDto> getLawsByType() {
        return ResponseEntity.ok(lawsService.getLawsByType());
    }

    @PostMapping(value = "/types")
    public ResponseEntity<?> updateLawsType(@RequestBody LawsByTypeDto lawsType) {
        lawsService.updateLawsType(lawsType);
        return ResponseEntity.ok().build();
    }

    /**
     * Найти максимальное наказание для указанного закона/статьи закона.
     *
     * @param searchText текст для поиска. Может содержать номер закона, статью закона или наименование закона. Примеры: "105", "105 часть 2", "Убийство".
     * @return LawDataDto - успешно. ErrorResponse - при возникновении ошибки.
     */
    @GetMapping(value = "/find/max_punishment/{searchText}", produces = "application/json")
    public ResponseEntity<?> findMaxPunishmentForLaw(@PathVariable String searchText, HttpServletRequest request) {
        if (StringUtils.isEmptyOrWhitespace(searchText)) {
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

            var lawWithMaxPunishment = lawsService.findLawWithMaxPunishment(article, text);

            return ResponseEntity.ok(localizationService.localize(convertToLawWithCrimeCategory(lawWithMaxPunishment), request));
        } catch (Throwable cause) {
            logger.error("Failed to get law data: {}", cause.getMessage());
            return ResponseEntity.status(404).body(new ErrorResponseDto(1, "Не удалось найти указанный закон", cause.getMessage()));
        }
    }

    private LawWithCrimeCategoryDto convertToLawWithCrimeCategory(Pair<LawData, Collection<Tuple<LawPartData, LawPartPunishData, Pair<CrimeCategoryData, CrimeCategoryData>>>> lawWithMaxPunishment) {
        var law = lawWithMaxPunishment.first();

        return LawWithCrimeCategoryDto.builder()
                .article(law.getArticle())
                .name(law.getName())
                .type(law.getType())
                .parts(lawWithMaxPunishment.second().stream().map(tuple -> {
                    var builder = LawPartWithCrimeCategoryDto.builder()
                            .name(tuple.first().getName())
                            .partPunishType(tuple.second().getType())
                            .categoryType(tuple.third().first().getCategoryType());

                    if (tuple.second().isLifeTime()) {
                        builder.isLifeTime(true);
                    } else {
                        builder.isLifeTime(false);
                        builder.punishType(tuple.second().getMax().getType());
                        builder.number(tuple.second().getMax().getNumber());
                    }

                    tuple.third().optionalSecond().ifPresent(category -> builder.optionalCategoryType(category.getCategoryType()));
                    return builder.build();
                }).toList())
                .build();
    }
}
