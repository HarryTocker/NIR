package core.parsers.converters.updater.updaters;

import static org.junit.jupiter.api.Assertions.*;

import com.find.law.portal.core.parsers.converters.updater.DataUpdater;
import com.find.law.portal.core.parsers.converters.updater.updaters.laws.LawIsNotValidDataUpdater;
import com.find.law.portal.core.parsers.converters.ArticleDataLines;
import com.find.law.portal.core.content.laws.LawData;
import com.find.law.portal.exceptions.LawDataUpdaterException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

public class LawIsNotValidDataUpdaterTest {
    private final DataUpdater<LawData> updater = new LawIsNotValidDataUpdater();

    @ParameterizedTest
    @MethodSource
    public void testNotValidLaw(List<String> lines) {
        ArticleDataLines line = new ArticleDataLines("Article", lines);
        LawData data = new LawData();
        assertThrowsExactly(LawDataUpdaterException.class, () -> updater.update(data, line));
    }

    @ParameterizedTest
    @MethodSource
    public void testValidLaw(List<String> lines) {
        ArticleDataLines line = new ArticleDataLines("Article", lines);
        LawData data = new LawData();
        assertDoesNotThrow(() -> updater.update(data, line));
    }

    private static Stream<Arguments> testNotValidLaw() {
        return Stream.of(
                Arguments.of(List.of("(Статья утратила силу - Федеральный закон от 07.12.2011 № 420-ФЗ)")),
                Arguments.of(List.of("123", "text", "simple text", "Статья утратила силу")),
                Arguments.of(List.of("статья утратила силу"))
        );
    }

    private static Stream<Arguments> testValidLaw() {
        return Stream.of(
                Arguments.of(List.of("text", "text text", "текст утратил силу")),
                Arguments.of(List.of("статья", "утратила", "силу", "проверка утратила силу"))
        );
    }
}
