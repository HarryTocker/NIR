package com.find.law.portal.core.content.laws;

import com.find.law.portal.controllers.dto.localized.laws.LocalizedLawPartPunishmentData;
import com.find.law.portal.controllers.dto.localized.punishments.LocalizedPunishmentData;
import com.find.law.portal.core.content.punishments.PunishmentData;
import com.find.law.portal.localization.Localizable;
import com.find.law.portal.localization.LocalizableTextInfo;
import com.find.law.portal.localization.LocalizableType;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.dozer.Mapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Данные наказания по части закона или категории преступления.
 */
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class LawPartPunishData implements Cloneable, Localizable<LocalizedLawPartPunishmentData> {
    /**
     * Тип наказания.
     */
    private LawPartPunishType type;

    /**
     * Минимальное наказание.
     */
    private PunishmentData min;

    /**
     * Максимальное наказание.
     */
    private PunishmentData max;

    /**
     * Опциональные наказания, которое могут быть применены с текущим.
     */
    private Collection<LawPartPunishData> optionals;

    /**
     * Является ли наказание пожизненным.
     */
    @Mapping("isLifeTime")
    private boolean isLifeTime;

    public LawPartPunishData() {
        this.type = LawPartPunishType.LIFE_IMPRISONMENT;
        this.min = null;
        this.max = null;
        this.optionals = new ArrayList<>(5);
        this.isLifeTime = true;
    }

    public LawPartPunishData(LawPartPunishType type, PunishmentData min, PunishmentData max) {
        if (type == LawPartPunishType.LIFE_IMPRISONMENT) {
            throw new IllegalArgumentException("LIFE_IMPRISONMENT type support only empty constructor");
        }
        this.type = type;
        this.min = min;
        this.max = max;
        this.optionals = new ArrayList<>(5);
        this.isLifeTime = false;
    }

    public LawPartPunishData(LawPartPunishType type, PunishmentData min, PunishmentData max, Collection<LawPartPunishData> optionals) {
        if (type == LawPartPunishType.LIFE_IMPRISONMENT) {
            throw new IllegalArgumentException("LIFE_IMPRISONMENT type support only empty constructor");
        }
        this.type = type;
        this.min = min;
        this.max = max;
        this.optionals = optionals == null ? new ArrayList<>(5) : optionals;
        this.isLifeTime = false;
    }

    @Override
    public LawPartPunishData clone() {
        return new LawPartPunishData(
                type,
                min == null ? null : min.clone(),
                max == null ? null : max.clone(),
                optionals.stream().map(LawPartPunishData::clone).toList(),
                isLifeTime
        );
    }

    @Override
    public Collection<LocalizableTextInfo<LocalizedLawPartPunishmentData>> getInfos() {
        return List.of(
                new LocalizableTextInfo<>(LocalizableType.ENUM, this::getType, ((entity, data) -> entity.setType((String) data))),
                new LocalizableTextInfo<>(LocalizableType.INSTANCE, this::getMin, ((entity, data) -> entity.setMin((LocalizedPunishmentData) data))),
                new LocalizableTextInfo<>(LocalizableType.INSTANCE, this::getMax, ((entity, data) -> entity.setMax((LocalizedPunishmentData) data))),
                new LocalizableTextInfo<>(LocalizableType.COLLECTION, this::getOptionals, ((entity, data) -> entity.setOptionals((Collection<LocalizedLawPartPunishmentData>) data))),
                new LocalizableTextInfo<>(LocalizableType.NONE, () -> isLifeTime, ((entity, data) -> entity.setIsLifeTime((Boolean) data)))
        );
    }

    @Override
    public LocalizedLawPartPunishmentData getLocalizedData() {
        return new LocalizedLawPartPunishmentData();
    }
}
