package com.find.law.portal.mappers;

import com.find.law.portal.controllers.dto.LawPartPunishmentDataDto;
import com.find.law.portal.repositories.entities.LawPartPunishEntity;
import org.springframework.stereotype.Component;

/**
 * Преобразователь сущности наказания части закона из БД к сущности ответа сервера (DTO Object).
 */
@Component
public class LawPartPunishmentDataMapper implements ForwardMapper<LawPartPunishEntity, LawPartPunishmentDataDto> {
    @Override
    public LawPartPunishmentDataDto map(LawPartPunishEntity entity) {
        LawPartPunishmentDataDto dto = new LawPartPunishmentDataDto();
        dto.setType(entity.getType());
        dto.setMinTime(entity.getMinTime());
        dto.setMaxTime(entity.getMaxTime());
        dto.setDateType(entity.getDateType());
        dto.setIsLifeTime(entity.getIsLifeTime());
        return dto;
    }
}
