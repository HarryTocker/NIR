package com.find.law.portal.core.parsers.converters.updater.updaters.laws;

import com.find.law.portal.core.content.laws.LawData;
import com.find.law.portal.core.content.laws.LawType;
import com.find.law.portal.core.parsers.converters.ArticleDataLines;
import com.find.law.portal.core.parsers.converters.updater.AbstractDataUpdater;
import com.find.law.portal.core.parsers.converters.updater.DataUpdaterPriority;
import com.find.law.portal.exceptions.LawDataUpdaterException;

import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

// TODO FIX-1 Исправить на корректное определение типа преступления статьи закона.
public class LawTypeFromArticleDataUpdater extends AbstractDataUpdater<LawData> {
    private final List<Pattern> negligencePatterns = List.of(
            Pattern.compile("по неосторожности")
    );

    public LawTypeFromArticleDataUpdater() {
        super(DataUpdaterPriority.MEDIUM);
    }

    @Override
    public void update(LawData data, ArticleDataLines articleData) throws LawDataUpdaterException {
        String name = articleData.article().toLowerCase(Locale.ROOT);
        if (isNegligenceLaw(name)) {
            data.setType(LawType.NEGLIGENCE);
            return;
        }

        for (String line : articleData.lines().stream().map(String::toLowerCase).toList()) {
            if (isNegligenceLaw(line)) {
                data.setType(LawType.NEGLIGENCE);
                return;
            }
        }

        data.setType(LawType.INTENTIONAL);
    }

    //NEGLIGENCE
    private boolean isNegligenceLaw(String line) {
        return checkPatterns(negligencePatterns, line);
    }

    private boolean checkPatterns(List<Pattern> patterns, String line) {
        for (Pattern pattern : patterns) {
            if (pattern.matcher(line).find()) {
                return true;
            }
        }

        return false;
    }
}
