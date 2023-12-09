package core.parsers.converters.categories;

import static org.junit.jupiter.api.Assertions.*;
import static interfaces.data.categories.WithPreparedCrimeCategoryData.*;
import static interfaces.data.WithPreparedArticleDataLines.getArticle15Categories;

import com.find.law.portal.core.content.categories.CrimeCategoryData;
import com.find.law.portal.core.parsers.converters.categories.GenericCrimeCategoryConverter;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GenericCrimeCategoryConverterTest {
    private final GenericCrimeCategoryConverter converter = new GenericCrimeCategoryConverter();

    @Test
    public void correctConvert() {
        List<CrimeCategoryData> actual = converter.convert(getArticle15Categories());
        List<CrimeCategoryData> expected = List.of(
                getIntentionalMinorData(),
                getNegligenceMinorData(),

                getIntentionalMediumData(),
                getNegligenceMediumData(),

                getIntentionalSeriousData(),
                getNegligenceSeriousData(),

                getIntentionalParticularlySeriousData()
        );

        assertEquals(expected, actual);
    }
}
