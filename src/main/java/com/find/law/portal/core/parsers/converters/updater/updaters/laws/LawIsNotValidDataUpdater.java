package com.find.law.portal.core.parsers.converters.updater.updaters.laws;

import com.find.law.portal.core.parsers.converters.updater.AbstractDataUpdater;
import com.find.law.portal.core.parsers.converters.updater.DataUpdaterPriority;
import com.find.law.portal.exceptions.LawDataUpdaterException;
import com.find.law.portal.core.parsers.converters.ArticleDataLines;
import com.find.law.portal.core.content.laws.LawData;

import java.util.Locale;

/**
 * Проверка данных валидности статьи для {@link LawData}.
 * <br>
 * Не изменяет поля, а выбрасывает исключение {@link LawDataUpdaterException} если статья утратила силу.
 */
public class LawIsNotValidDataUpdater extends AbstractDataUpdater<LawData> {
    private static final String LAW_IS_NOT_VALID = "статья утратила силу";

    public LawIsNotValidDataUpdater() {
        super(DataUpdaterPriority.VERY_HIGH);
    }

    @Override
    public void update(LawData data, ArticleDataLines articleData) throws LawDataUpdaterException {
        if (articleData.article().toLowerCase(Locale.ROOT).contains(LAW_IS_NOT_VALID)) {
            throw new LawDataUpdaterException("Law is not valid: Article has expired");
        }

        if (articleData.lines().size() < 2 || articleData.lines().stream().anyMatch(l -> l.toLowerCase(Locale.ROOT).contains(LAW_IS_NOT_VALID))) {
            throw new LawDataUpdaterException("Law is not valid: Article has expired");
        }
    }
}
