package core.parsers.converters.updater.updaters;

import static org.junit.jupiter.api.Assertions.*;
import static interfaces.data.laws.WithPreparedLawData.*;
import static interfaces.data.WithPreparedArticleDataLines.*;

import com.find.law.portal.core.parsers.converters.updater.DataUpdater;
import com.find.law.portal.exceptions.LawDataUpdaterException;
import com.find.law.portal.core.parsers.converters.updater.updaters.laws.LawArticleDataUpdater;
import com.find.law.portal.core.parsers.converters.ArticleDataLines;
import com.find.law.portal.core.content.laws.LawData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

@DisplayName("Correct parsing of article number and title.")
public class LawArticleDataUpdaterTest {
    private final DataUpdater<LawData> updater = new LawArticleDataUpdater();

    @ParameterizedTest
    @MethodSource
    @DisplayName("Updater with valid data should set number and title values")
    public void correctLawArticleSet(ArticleDataLines line, LawData expected) {
        LawData actual = new LawData();
        assertDoesNotThrow(() -> updater.update(actual, line), "Корректные данные не должны возвращать ошибку");
        assertEquals(expected.getArticle(), actual.getArticle(), "Номер статьи не равен ожидаемому");
        assertEquals(expected.getName(), actual.getName(), "Наименование статьи не равно ожидаемому");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Текст 105.",
            "   606.",
            "Test 100",
            "Test 100.",
            "Test 100.1.",
            " 50.5. ",
            "     ",
            "",
            "Статья",
            "Статья.",
            "Статья Название.",
            "Статья 105",
            "Статья 105.",
            "Статья 105.1.",
            "Статья 105.    1",
            "Статья 105.     "
    })
    @DisplayName("Updater with incorrect data should throw exception")
    public void incorrectLawArticleThrows(String article) {
        ArticleDataLines line = new ArticleDataLines(article, List.of());
        LawData data = new LawData();
        assertThrowsExactly(LawDataUpdaterException.class, () -> updater.update(data, line), "Некорректные данные должны вернуть ошибку");
    }

    private static Stream<Arguments> correctLawArticleSet() {
        return Stream.of(
                Arguments.of(
                        getArticle105(),
                        getLawData105()
                ),
                Arguments.of(
                        getArticle106(),
                        getLawData106()
                ),
                Arguments.of(
                        getArticle114(),
                        getLawData114()
                ),
                Arguments.of(
                        getArticle115(),
                        getLawData115()
                ),
                Arguments.of(
                        getArticle116(),
                        getLawData116()
                ),
                Arguments.of(
                        getArticle124P1(),
                        getLawData124P1()
                ),
                Arguments.of(
                        getArticle158(),
                        getLawData158()
                ),
                Arguments.of(
                        getArticle200P1(),
                        getLawData200P1()
                ),
                Arguments.of(
                        getArticle333(),
                        getLawData333()
                ),
                Arguments.of(
                        getArticle334(),
                        getLawData334()
                ),
                Arguments.of(
                        getArticle335(),
                        getLawData335()
                )
        );
    }
}
