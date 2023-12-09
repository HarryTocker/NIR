package core.parsers.converters.updater.support;

import com.find.law.portal.core.parsers.converters.updater.support.LawTypesStorage;
import com.find.law.portal.core.parsers.converters.updater.support.LawTypesStorageImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.ResourcesUtils;

import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LawTypesStorageTest {
    private static LawTypesStorage LAW_TYPE_STORAGE;

    @BeforeAll
    public static void beforeAll() throws IOException {
        LAW_TYPE_STORAGE = new LawTypesStorageImpl(ResourcesUtils.getLawTypesPath().toString());
    }

    @Test
    public void testCorrectContentParse() {
        final Set<String> expectedIntentional = Set.of("100", "101", "102");
        final Set<String> expectedNegligence = Set.of("200", "201", "202");
        final Set<String> expectedUnknown = Set.of("300", "301", "302");

        assertEquals(expectedIntentional.size(), LAW_TYPE_STORAGE.getIntentional().size());
        assertEquals(expectedNegligence.size(), LAW_TYPE_STORAGE.getNegligence().size());
        assertEquals(expectedUnknown.size(), LAW_TYPE_STORAGE.getUnknown().size());

        assertEquals(expectedIntentional, LAW_TYPE_STORAGE.getIntentional());
        assertEquals(expectedNegligence, LAW_TYPE_STORAGE.getNegligence());
        assertEquals(expectedUnknown, LAW_TYPE_STORAGE.getUnknown());
    }
}
