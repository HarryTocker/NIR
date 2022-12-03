package com.find.law.portal.mappers;

import com.find.law.portal.controllers.dto.LawPartDataDto;
import com.find.law.portal.repositories.entities.LawPartEntity;
import org.springframework.stereotype.Component;

/**
 * Преобразователь сущности части закона из БД к сущности ответа сервера (DTO Object).
 */
@Component
public class LawPartDataMapper implements ForwardMapper<LawPartEntity, LawPartDataDto> {
    private final LawPartPunishmentDataMapper punishmentDataMapper;

    public LawPartDataMapper(LawPartPunishmentDataMapper punishmentDataMapper) {
        this.punishmentDataMapper = punishmentDataMapper;
    }

    @Override
    public LawPartDataDto map(LawPartEntity entity) {
        LawPartDataDto dto = new LawPartDataDto();
        dto.setPart(entity.getName());
        dto.setPunishments(punishmentDataMapper.map(entity.getPunishments()));
        return dto;
    }
}
