package com.find.law.portal.core.parsers.converters.updater.updaters.laws;

import com.find.law.portal.core.content.laws.LawData;
import com.find.law.portal.core.content.laws.LawType;
import com.find.law.portal.core.parsers.converters.ArticleDataLines;
import com.find.law.portal.core.parsers.converters.updater.AbstractDataUpdater;
import com.find.law.portal.core.parsers.converters.updater.DataUpdaterPriority;
import com.find.law.portal.core.parsers.converters.updater.support.LawTypesStorage;
import com.find.law.portal.exceptions.LawDataUpdaterException;

public class LawTypeFromFileDataUpdater extends AbstractDataUpdater<LawData> {
    private final LawTypesStorage lawTypesStorage;

    public LawTypeFromFileDataUpdater(LawTypesStorage lawTypesStorage) {
        super(DataUpdaterPriority.LOW);
        this.lawTypesStorage = lawTypesStorage;
    }

    @Override
    public void update(LawData data, ArticleDataLines articleData) throws LawDataUpdaterException {
        if (lawTypesStorage.getIntentional().contains(data.getArticle())) {
            data.setType(LawType.INTENTIONAL);
            return;
        }

        if (lawTypesStorage.getNegligence().contains(data.getArticle())) {
            data.setType(LawType.NEGLIGENCE);
            return;
        }

        if (lawTypesStorage.getUnknown().contains(data.getArticle())) {
            data.setType(LawType.UNKNOWN);
        }
    }
}
