package com.find.law.portal.core.content.laws;

import com.find.law.portal.controllers.dto.localized.laws.LocalizedLawData;
import com.find.law.portal.controllers.dto.localized.laws.LocalizedLawPartData;
import com.find.law.portal.localization.Localizable;
import com.find.law.portal.localization.LocalizableTextInfo;
import lombok.*;

import java.util.Collection;
import java.util.List;

import static com.find.law.portal.localization.LocalizableType.*;

/**
 * Данные закона.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class LawData implements Cloneable, Localizable<LocalizedLawData> {
    /**
     * Статья закона.
     */
    private String article;

    /**
     * Наименование статьи.
     */
    private String name;

    /**
     * Тип преступления.
     */
    private LawType type = LawType.UNKNOWN;

    /**
     * Части статьи.
     */
    private Collection<LawPartData> parts;

    @Override
    public LawData clone() {
        return new LawData(article, name, type, parts.stream().map(LawPartData::clone).toList());
    }

    @Override
    public Collection<LocalizableTextInfo<LocalizedLawData>> getInfos() {
        return List.of(
                new LocalizableTextInfo<>(NONE, this::getArticle, (entity, data) -> entity.setArticle((String) data)),
                new LocalizableTextInfo<>(NONE, this::getName, (entity, data) -> entity.setName((String) data)),
                new LocalizableTextInfo<>(ENUM, this::getType, (entity, data) -> entity.setType((String) data)),
                new LocalizableTextInfo<>(COLLECTION, this::getParts, ((entity, data) -> entity.setParts((Collection<LocalizedLawPartData>) data)))
        );
    }

    @Override
    public LocalizedLawData getLocalizedData() {
        return new LocalizedLawData();
    }
}
