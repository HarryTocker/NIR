package mappers;

import static interfaces.mappers.WithGenericBeanMapper.MAPPER;
import static interfaces.data.categories.WithPreparedCrimeCategoryData.*;
import static interfaces.data.categories.WithPreparedCrimeCategoryEntity.*;

import com.find.law.portal.core.content.categories.CrimeCategoryData;
import com.find.law.portal.repositories.entities.categories.CrimeCategoryEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Correct mapping between CrimeCategoryData and CrimeCategoryEntity")
public class TwoSidesCrimeCategoryDataAndCrimeCategoryEntityMapperTest {
    @ParameterizedTest
    @MethodSource
    @DisplayName("CrimeCategoryData to CrimeCategoryEntity")
    public void testCrimeCategoryDataToCrimeCategoryEntityMapping(CrimeCategoryData category, CrimeCategoryEntity expected) {
        CrimeCategoryEntity actual = MAPPER.map(category, CrimeCategoryEntity.class);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("CrimeCategoryEntity to CrimeCategoryData")
    public void testCrimeCategoryEntityToCrimeCategoryDataMapping(CrimeCategoryEntity entity, CrimeCategoryData expected) {
        CrimeCategoryData actual = MAPPER.map(entity, CrimeCategoryData.class);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> testCrimeCategoryDataToCrimeCategoryEntityMapping() {
        return Stream.of(
                Arguments.of(
                        getIntentionalMinorData(),
                        getIntentionalMinorEntity()
                ),

                Arguments.of(
                        getNegligenceMinorData(),
                        getNegligenceMinorEntity()
                ),

                Arguments.of(
                        getIntentionalMediumData(),
                        getIntentionalMediumEntity()
                ),

                Arguments.of(
                        getNegligenceMediumData(),
                        getNegligenceMediumEntity()
                ),

                Arguments.of(
                        getIntentionalSeriousData(),
                        getIntentionalSeriousEntity()
                ),

                Arguments.of(
                        getNegligenceSeriousData(),
                        getNegligenceSeriousEntity()
                ),

                Arguments.of(
                        getIntentionalParticularlySeriousData(),
                        getIntentionalParticularlySeriousEntity()
                )
        );
    }

    private static Stream<Arguments> testCrimeCategoryEntityToCrimeCategoryDataMapping() {
        return Stream.of(
                Arguments.of(
                        getIntentionalMinorEntity(),
                        getIntentionalMinorData()
                ),

                Arguments.of(
                        getNegligenceMinorEntity(),
                        getNegligenceMinorData()
                ),

                Arguments.of(
                        getIntentionalMediumEntity(),
                        getIntentionalMediumData()
                ),

                Arguments.of(
                        getNegligenceMediumEntity(),
                        getNegligenceMediumData()
                ),

                Arguments.of(
                        getIntentionalSeriousEntity(),
                        getIntentionalSeriousData()
                ),

                Arguments.of(
                        getNegligenceSeriousEntity(),
                        getNegligenceSeriousData()
                ),

                Arguments.of(
                        getIntentionalParticularlySeriousEntity(),
                        getIntentionalParticularlySeriousData()
                )
        );
    }
}
