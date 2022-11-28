package com.find.law.portal.controllers;

import com.find.law.portal.controllers.dto.ErrorResponse;
import com.find.law.portal.controllers.dto.LawDataDto;
import com.find.law.portal.controllers.dto.LawPartDataDto;
import com.find.law.portal.controllers.dto.LawPartPunishmentDataDto;
import com.find.law.portal.repositories.entities.LawEntity;
import com.find.law.portal.repositories.entities.LawPartEntity;
import com.find.law.portal.repositories.entities.LawPartPunishEntity;
import com.find.law.portal.services.LawsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("api/laws")
public class LawsController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String articleRegex = "(\\d{3}\\.\\d+)|(\\d{3})";

    private static final String partRegex = "(пункт \\d+)|(часть \\d+)";

    private final Pattern articlePattern = Pattern.compile(articleRegex);

    private final Pattern partPattern = Pattern.compile(partRegex);

    private final LawsService lawsService;

    public LawsController(LawsService lawsService) {
        this.lawsService = lawsService;
    }

    /**
     * Найти максимальное наказание для указанного закона/статьи закона.
     * @param searchText текст для поиска. Может содержать номер закона, статью закона или наименование закона. Примеры: "105", "105 часть 2", "Убийство".
     * @return LawDataDto - успешно. ErrorResponse - при возникновении ошибки.
     */
    @GetMapping(value = "/find/max_punishment/{searchText}", produces = "application/json")
    public ResponseEntity<?> findMaxPunishmentForLaw(@PathVariable String searchText) {
        if (searchText == null || StringUtils.isEmptyOrWhitespace(searchText)) {
            return ResponseEntity.badRequest().body(new ErrorResponse(0, "Невозможно выполнить поиск по пустой строке", null));
        }

        try {
            String article = null;
            String text = searchText.replaceAll(articleRegex, "").replaceAll(partRegex, "").replaceAll("УК", "").replaceAll("РФ", "");
            Matcher articleMatcher = articlePattern.matcher(searchText);
            if (articleMatcher.find()) {
                article = articleMatcher.group();
            }

            LawEntity law = lawsService.findLaw(article, text);

            LawDataDto lawDto = new LawDataDto();
            lawDto.setArticle(law.getArticle());
            lawDto.setName(law.getName());
            List<LawPartEntity> parts = law.getParts();
            ArrayList<LawPartDataDto> partsDto = new ArrayList<>(parts.size());
            if (parts.size() == 1) {
                LawPartDataDto partDto = new LawPartDataDto();
                partDto.setPart(parts.get(0).getName());
                LawPartPunishEntity punish = lawsService.findMaxPunishment(parts.get(0));
                partDto.setPunishment(new LawPartPunishmentDataDto(punish.getType(), punish.getMinTime(), punish.getMaxTime(), punish.getDateType(), punish.getIsLifeTime()));
                partsDto.add(partDto);
            } else {
                Matcher partMatcher = partPattern.matcher(searchText);
                final String partText = partMatcher.find() ? partMatcher.group().replaceAll("\\D+", "") : null;

                if (partText != null) {
                    LawPartEntity part = parts.stream().filter(p -> p.getName().startsWith(partText)).findFirst().orElse(parts.get(0));
                    LawPartDataDto partDto = new LawPartDataDto();
                    partDto.setPart(part.getName());
                    LawPartPunishEntity punish = lawsService.findMaxPunishment(parts.get(0));
                    partDto.setPunishment(new LawPartPunishmentDataDto(punish.getType(), punish.getMinTime(), punish.getMaxTime(), punish.getDateType(), punish.getIsLifeTime()));
                    partsDto.add(partDto);
                } else {
                    for (LawPartEntity part : parts) {
                        LawPartDataDto partDto = new LawPartDataDto();
                        partDto.setPart(part.getName());
                        LawPartPunishEntity punish = lawsService.findMaxPunishment(parts.get(0));
                        partDto.setPunishment(new LawPartPunishmentDataDto(punish.getType(), punish.getMinTime(), punish.getMaxTime(), punish.getDateType(), punish.getIsLifeTime()));
                        partsDto.add(partDto);
                    }
                }
            }

            lawDto.setParts(partsDto);
            return ResponseEntity.ok(lawDto);
        } catch (Exception cause) {
            logger.error("Failed to get law data: {}", cause.getMessage());
            return ResponseEntity.status(404).body(new ErrorResponse(1, "Не удалось найти указанный закон", cause.getMessage()));
        }
    }
}
