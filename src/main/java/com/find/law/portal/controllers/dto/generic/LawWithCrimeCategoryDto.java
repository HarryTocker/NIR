package com.find.law.portal.controllers.dto.generic;

import com.find.law.portal.controllers.dto.localized.laws.LocalizedLawPartWithCrimeCategoryDto;
import com.find.law.portal.controllers.dto.localized.laws.LocalizedLawWithCrimeCategoryDto;
import com.find.law.portal.core.content.laws.LawPartData;
import com.find.law.portal.core.content.laws.LawType;
import com.find.law.portal.localization.Localizable;
import com.find.law.portal.localization.LocalizableTextInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

import static com.find.law.portal.localization.LocalizableType.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LawWithCrimeCategoryDto implements Localizable<LocalizedLawWithCrimeCategoryDto> {
    private String article;

    private String name;

    private LawType type;

    private Collection<LawPartWithCrimeCategoryDto> parts;

    @Override
    public Collection<LocalizableTextInfo<LocalizedLawWithCrimeCategoryDto>> getInfos() {
        return List.of(
                new LocalizableTextInfo<>(NONE, this::getArticle, (entity, data) -> entity.setArticle((String) data)),
                new LocalizableTextInfo<>(NONE, this::getName, (entity, data) -> entity.setName((String) data)),
                new LocalizableTextInfo<>(ENUM, this::getType, (entity, data) -> entity.setType((String) data)),
                new LocalizableTextInfo<>(COLLECTION, this::getParts, ((entity, data) -> entity.setParts((Collection<LocalizedLawPartWithCrimeCategoryDto>) data)))
        );
    }

    @Override
    public LocalizedLawWithCrimeCategoryDto getLocalizedData() {
        return new LocalizedLawWithCrimeCategoryDto();
    }
}
