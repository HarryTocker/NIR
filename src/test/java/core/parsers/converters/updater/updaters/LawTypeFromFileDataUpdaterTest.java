package core.parsers.converters.updater.updaters;

import static org.mockito.Mockito.*;

import com.find.law.portal.core.parsers.converters.updater.support.LawTypesStorage;
import com.find.law.portal.core.parsers.converters.updater.updaters.laws.LawTypeFromFileDataUpdater;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class LawTypeFromFileDataUpdaterTest {
    private static LawTypesStorage lawTypesStorage;

    private static LawTypeFromFileDataUpdater updater;

    @BeforeEach
    void beforeEach() {
        lawTypesStorage = mock(LawTypesStorage.class);
        updater = new LawTypeFromFileDataUpdater(lawTypesStorage);
    }

    @Test
    void testNegligenceType() {
        when(lawTypesStorage.getNegligence()).thenReturn(Set.of("100", "110"));

    }
}
