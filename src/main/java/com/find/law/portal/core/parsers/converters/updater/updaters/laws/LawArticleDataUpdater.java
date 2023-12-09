package com.find.law.portal.core.parsers.converters.updater.updaters.laws;

import com.find.law.portal.exceptions.LawDataUpdaterException;
import com.find.law.portal.core.parsers.converters.updater.AbstractDataUpdater;
import com.find.law.portal.core.parsers.converters.updater.DataUpdaterPriority;
import com.find.law.portal.core.parsers.converters.ArticleDataLines;
import com.find.law.portal.core.content.laws.LawData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Обновление данных номера и названия статьи закона для {@link LawData}.
 * <br>
 * Выбрасывает исключение {@link LawDataUpdaterException}, если не удалось получить номер или название статьи.
 */
public class LawArticleDataUpdater extends AbstractDataUpdater<LawData> {
    /**
     * Regex паттерн номера статьи.
     */
    private static final Pattern ARTICLE_PATTERN = Pattern.compile("Статья (\\d+.\\d+).");
    /**
     * Regex паттерн названия статьи.
     */
    private static final Pattern NAME_PATTERN = Pattern.compile("Статья \\S+ (\\D+)");

    public LawArticleDataUpdater() {
        super(DataUpdaterPriority.HIGH);
    }

    @Override
    public void update(LawData data, ArticleDataLines articleData) throws LawDataUpdaterException {
        data.setArticle(getArticle(articleData));
        data.setName(getName(articleData));
    }

    /**
     * Получить номер статьи.
     *
     * @param lawData подготовленные данные статьи.
     * @return номер статьи.
     * @throws LawDataUpdaterException не удалось получить номер статьи. Исключение не является критическим так как
     * отсутствие номера может говорить о статье утратившей силу или не являющейся действительной.
     */
    private String getArticle(ArticleDataLines lawData) throws LawDataUpdaterException {
        Matcher matcher = ARTICLE_PATTERN.matcher(lawData.article());
        if (!matcher.find()) {
            throw new LawDataUpdaterException("Input text [%s] did not match article pattern".formatted(lawData.article()));
        }

        String article = matcher.group(1);
        if (!article.contains(".") && article.length() > 3) {
            article = article.substring(0, 3) + "." + article.substring(3);
        }

        if (article.isBlank()) {
            throw new LawDataUpdaterException("Input text [%s] parsed to empty article".formatted(lawData.article()));
        }

        return article;
    }

    /**
     * Получить название статьи.
     *
     * @param lawData подготовленные данные статьи.
     * @return название статьи.
     * @throws LawDataUpdaterException не удалось получить название статьи. Исключение не является критическим так как
     * отсутствие названия может говорить о статье утратившей силу или не являющейся действительной.
     */
    private String getName(ArticleDataLines lawData) throws LawDataUpdaterException {
        Matcher matcher = NAME_PATTERN.matcher(lawData.article());
        if (!matcher.find()) {
            throw new LawDataUpdaterException("Input text [%s] did not match article name pattern".formatted(lawData.article()));
        }

        String name = matcher.group(1);

        if (name.isBlank()) {
            throw new LawDataUpdaterException("Input text [%s] parsed to empty article name".formatted(lawData.article()));
        }

        return name;
    }
}
