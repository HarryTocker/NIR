package com.find.law.portal.core.parsers.converters.updater.support;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class LawTypesStorageImpl implements LawTypesStorage {
    private final HashSet<String> negligence;

    private final HashSet<String> intentional;

    private final HashSet<String> unknown;

    public LawTypesStorageImpl(String lawTypesPath) throws IOException {
        File lawTypesFile = Path.of(lawTypesPath).toFile();
        if (!lawTypesFile.exists()) {
            throw new IllegalStateException("File with law types does not exist");
        }

        ObjectMapper mapper = new ObjectMapper();
        LawTypesDto types = mapper.readValue(lawTypesFile, LawTypesDto.class);

        this.negligence = new HashSet<>(types.negligence());
        this.intentional = new HashSet<>(types.intentional());
        this.unknown = new HashSet<>(types.unknown());
    }

    @Override
    public Set<String> getNegligence() {
        return negligence;
    }

    @Override
    public Set<String> getIntentional() {
        return intentional;
    }

    @Override
    public Set<String> getUnknown() {
        return unknown;
    }
}
