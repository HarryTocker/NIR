package com.find.law.portal.core.content.punishments;

import com.find.law.portal.controllers.dto.localized.punishments.LocalizedPunishmentData;
import com.find.law.portal.localization.Localizable;
import com.find.law.portal.localization.LocalizableTextInfo;
import com.find.law.portal.localization.LocalizableType;
import com.ibm.icu.text.NumberFormat;
import com.ibm.icu.text.RuleBasedNumberFormat;
import lombok.*;

import java.text.ParseException;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

/**
 * Данные срока наказания.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class PunishmentData implements Cloneable, Localizable<LocalizedPunishmentData> {
    private static final NumberFormat FORMATTER = new RuleBasedNumberFormat(Locale.forLanguageTag("ru"), RuleBasedNumberFormat.SPELLOUT);

    private String text;

    private Double number;

    private PunishmentType type;

    public PunishmentData(String text, PunishmentType type) {
        try {
            this.text = text;
            this.number = FORMATTER.parse(text).doubleValue();
            this.type = type;
        } catch (ParseException cause) {
            throw new IllegalArgumentException("Incorrect number value format [%s]".formatted(text), cause);
        }
    }

    public PunishmentData(double number, PunishmentType type) {
        this.text = FORMATTER.format(number);
        this.number = number;
        this.type = type;
    }

    @Override
    public PunishmentData clone() {
        return new PunishmentData(text, number, type);
    }

    @Override
    public Collection<LocalizableTextInfo<LocalizedPunishmentData>> getInfos() {
        return List.of(
                new LocalizableTextInfo<>(LocalizableType.NONE, this::getText, ((entity, data) -> entity.setText((String) data))),
                new LocalizableTextInfo<>(LocalizableType.NONE, this::getNumber, ((entity, data) -> entity.setValue((Double) data))),
                new LocalizableTextInfo<>(LocalizableType.ENUM, this::getType, ((entity, data) -> entity.setType((String) data)))
        );
    }

    @Override
    public LocalizedPunishmentData getLocalizedData() {
        return new LocalizedPunishmentData();
    }
}
