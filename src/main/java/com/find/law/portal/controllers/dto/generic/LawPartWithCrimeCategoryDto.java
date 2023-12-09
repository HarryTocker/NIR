package com.find.law.portal.controllers.dto.generic;

import com.find.law.portal.controllers.dto.localized.laws.LocalizedLawPartWithCrimeCategoryDto;
import com.find.law.portal.core.content.categories.CrimeCategoryType;
import com.find.law.portal.core.content.laws.LawPartPunishType;
import com.find.law.portal.core.content.punishments.PunishmentType;
import com.find.law.portal.localization.Localizable;
import com.find.law.portal.localization.LocalizableTextInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

import static com.find.law.portal.localization.LocalizableType.ENUM;
import static com.find.law.portal.localization.LocalizableType.NONE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LawPartWithCrimeCategoryDto implements Localizable<LocalizedLawPartWithCrimeCategoryDto> {
    private String name;

    private LawPartPunishType partPunishType;

    private PunishmentType punishType;

    private CrimeCategoryType categoryType;

    private Double number;

    private CrimeCategoryType optionalCategoryType;

    private boolean isLifeTime;

    @Override
    public Collection<LocalizableTextInfo<LocalizedLawPartWithCrimeCategoryDto>> getInfos() {
        return List.of(
                new LocalizableTextInfo<>(NONE, this::getName, (entity, data) -> entity.setName((String) data)),
                new LocalizableTextInfo<>(ENUM, this::getPartPunishType, (entity, data) -> entity.setPartPunishType((String) data)),
                new LocalizableTextInfo<>(ENUM, this::getPunishType, (entity, data) -> entity.setPunishType((String) data)),
                new LocalizableTextInfo<>(ENUM, this::getCategoryType, (entity, data) -> entity.setCategoryType((String) data)),
                new LocalizableTextInfo<>(NONE, this::getNumber, ((entity, data) -> entity.setNumber((Double) data))),
                new LocalizableTextInfo<>(ENUM, this::getOptionalCategoryType, (entity, data) -> entity.setOptionalCategoryType((String) data)),
                new LocalizableTextInfo<>(NONE, this::isLifeTime, ((entity, data) -> entity.setLifeTime((Boolean) data)))
        );
    }

    @Override
    public LocalizedLawPartWithCrimeCategoryDto getLocalizedData() {
        return new LocalizedLawPartWithCrimeCategoryDto();
    }
}
