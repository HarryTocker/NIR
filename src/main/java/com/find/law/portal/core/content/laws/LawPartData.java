package com.find.law.portal.core.content.laws;

import java.util.Collection;
import java.util.List;

import com.find.law.portal.controllers.dto.localized.laws.LocalizedLawPartData;
import com.find.law.portal.controllers.dto.localized.laws.LocalizedLawPartPunishmentData;
import com.find.law.portal.localization.Localizable;
import com.find.law.portal.localization.LocalizableTextInfo;
import com.find.law.portal.localization.LocalizableType;
import lombok.*;

/**
 * Данные части закона.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class LawPartData implements Cloneable, Localizable<LocalizedLawPartData> {
    /**
     * Наименование части закона.
     */
    private String name;

    /**
     * Возможные наказания части закона.
     */
    private Collection<LawPartPunishData> punishments;

    @Override
    public LawPartData clone() {
        return new LawPartData(name, punishments.stream().map(LawPartPunishData::clone).toList());
    }

    @Override
    public Collection<LocalizableTextInfo<LocalizedLawPartData>> getInfos() {
        return List.of(
                new LocalizableTextInfo<>(LocalizableType.NONE, this::getName, ((entity, data) -> entity.setName((String) data))),
                new LocalizableTextInfo<>(LocalizableType.COLLECTION, this::getPunishments, ((entity, data) -> entity.setPunishments((Collection<LocalizedLawPartPunishmentData>) data)))
        );
    }

    @Override
    public LocalizedLawPartData getLocalizedData() {
        return new LocalizedLawPartData();
    }
}
