package com.find.law.portal.mappers;

import com.find.law.portal.controllers.dto.LawDataDto;
import com.find.law.portal.repositories.entities.LawEntity;
import org.springframework.stereotype.Component;

/**
 * Преобразователь сущности закона из БД к сущности ответа сервера (DTO Object).
 */
@Component
public class LawDataMapper implements ForwardMapper<LawEntity, LawDataDto> {
    private final LawPartDataMapper partDataMapper;

    public LawDataMapper(LawPartDataMapper partDataMapper) {
        this.partDataMapper = partDataMapper;
    }

    @Override
    public LawDataDto map(LawEntity entity) {
        LawDataDto dto = new LawDataDto();
        dto.setArticle(entity.getArticle());
        dto.setName(entity.getName());
        dto.setParts(partDataMapper.map(entity.getParts()));
        return dto;
    }
}
