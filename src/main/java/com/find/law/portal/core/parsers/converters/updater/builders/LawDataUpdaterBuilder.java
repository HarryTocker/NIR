package com.find.law.portal.core.parsers.converters.updater.builders;

import com.find.law.portal.core.content.laws.LawData;
import com.find.law.portal.core.parsers.converters.updater.AbstractDataUpdaterBuilder;
import com.find.law.portal.core.parsers.converters.updater.support.LawTypesStorage;
import com.find.law.portal.core.parsers.converters.updater.support.LawTypesStorageImpl;
import com.find.law.portal.core.parsers.converters.updater.updaters.laws.*;
import com.find.law.portal.core.parsers.punishments.GenericPunishmentsParser;

import java.io.IOException;

public class LawDataUpdaterBuilder extends AbstractDataUpdaterBuilder<LawData> {
    private final String lawTypesPath;

    public LawDataUpdaterBuilder(String lawTypesPath) {
        this.lawTypesPath = lawTypesPath;
    }

    public LawDataUpdaterBuilder addArticleUpdater() {
        addUpdater(new LawArticleDataUpdater());
        return this;
    }

    public LawDataUpdaterBuilder addLawIsNotValidUpdater() {
        addUpdater(new LawIsNotValidDataUpdater());
        return this;
    }

    public LawDataUpdaterBuilder addPartUpdater() {
        addUpdater(new LawPartDataUpdater(new GenericPunishmentsParser()));
        return this;
    }

    public LawDataUpdaterBuilder addLawTypeFromArticleDataUpdater() {
        addUpdater(new LawTypeFromArticleDataUpdater());
        return this;
    }

    public LawDataUpdaterBuilder addLawTypeFromFileDataUpdater() {
        try {
            addUpdater(new LawTypeFromFileDataUpdater(new LawTypesStorageImpl(lawTypesPath)));
            return this;
        } catch (IOException cause) {
            throw new IllegalStateException("Could not add law type from file updater", cause);
        }
    }
}
