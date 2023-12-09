package core.parsers.converters.updater.builders;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.find.law.portal.core.content.laws.LawData;
import com.find.law.portal.core.parsers.converters.updater.DataUpdater;
import com.find.law.portal.core.parsers.converters.updater.DataUpdaterPriority;
import com.find.law.portal.core.parsers.converters.updater.builders.LawDataUpdaterBuilder;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LawDataUpdaterBuilderTest {
    @Test
    public void correctBuilderSortingTest() {
        List<DataUpdaterPriority> expectedPriority = List.of(
                DataUpdaterPriority.VERY_HIGH,
                DataUpdaterPriority.HIGH,
                DataUpdaterPriority.MEDIUM,
                DataUpdaterPriority.LOW
        );

        DataUpdater<LawData> veryHigh = mockUpdaterWithPriority(DataUpdaterPriority.VERY_HIGH);
        DataUpdater<LawData> high = mockUpdaterWithPriority(DataUpdaterPriority.HIGH);
        DataUpdater<LawData> medium = mockUpdaterWithPriority(DataUpdaterPriority.MEDIUM);
        DataUpdater<LawData> low = mockUpdaterWithPriority(DataUpdaterPriority.LOW);

        LawDataUpdaterBuilder builder = new LawDataUpdaterBuilder(null);
        List<DataUpdaterPriority> actualPriority = builder
                .addUpdater(low)
                .addUpdater(high)
                .addUpdater(medium)
                .addUpdater(veryHigh)
                .build()
                .stream()
                .map(DataUpdater::priority)
                .toList();

        assertEquals(expectedPriority, actualPriority, "Построитель должен вернуть отсортированные по приоритету обновления данных");
    }

    private static DataUpdater<LawData> mockUpdaterWithPriority(DataUpdaterPriority priority) {
        DataUpdater<LawData> updater = mock(DataUpdater.class);
        when(updater.priority()).thenReturn(priority);
        return updater;
    }
}
