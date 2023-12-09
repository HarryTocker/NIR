package core.parsers.converters.updater.updaters;

import static interfaces.data.WithPreparedArticleDataLines.getArticle105;
import static interfaces.data.WithPreparedArticleDataLines.getArticle106;
import static interfaces.data.laws.WithPreparedLawData.getLawData105;
import static interfaces.data.laws.WithPreparedLawData.getLawData106;
import static org.junit.jupiter.api.Assertions.*;

import com.find.law.portal.core.parsers.converters.updater.DataUpdater;
import com.find.law.portal.core.parsers.punishments.GenericPunishmentsParser;
import com.find.law.portal.core.parsers.converters.updater.updaters.laws.LawPartDataUpdater;
import com.find.law.portal.core.parsers.converters.ArticleDataLines;
import com.find.law.portal.core.content.laws.LawData;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

public class LawPartDataUpdaterTest {
    private final DataUpdater<LawData> updater = new LawPartDataUpdater(new GenericPunishmentsParser());

    @ParameterizedTest
    @MethodSource
    public void testCorrectParts(ArticleDataLines line, LawData expected) {
        LawData actual = new LawData(expected.getArticle(), expected.getName(), expected.getType(), List.of());
        assertDoesNotThrow(() -> updater.update(actual, line));
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> testCorrectParts() {
        return Stream.of(
                Arguments.of(
                        getArticle105(),
                        getLawData105()
                ),
                Arguments.of(
                        getArticle106(),
                        getLawData106()
                )
        );
    }
}
