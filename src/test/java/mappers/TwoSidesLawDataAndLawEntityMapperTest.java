package mappers;

import static interfaces.mappers.WithGenericBeanMapper.MAPPER;
import static interfaces.data.laws.WithPreparedLawData.*;
import static interfaces.data.laws.WithPreparedLawEntity.*;

import com.find.law.portal.core.content.laws.LawData;
import com.find.law.portal.repositories.entities.laws.LawEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Correct mapping between LawData and LawEntity")
public class TwoSidesLawDataAndLawEntityMapperTest {
    @ParameterizedTest
    @MethodSource
    @DisplayName("LawData to LawEntity")
    public void testLawDataToLawEntityMapping(LawData lawData, LawEntity expected) {
        LawEntity actual = MAPPER.map(lawData, LawEntity.class);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("LawEntity to LawData")
    public void testLawEntityToLawDataMapping(LawEntity lawEntity, LawData expected) {
        LawData actual = MAPPER.map(lawEntity, LawData.class);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> testLawDataToLawEntityMapping() {
        return Stream.of(
                Arguments.of(
                        getLawData105(),
                        getLawEntity105()
                ),
                Arguments.of(
                        getLawData106(),
                        getLawEntity106()
                ),
                Arguments.of(
                        getLawData114(),
                        getLawEntity114()
                ),
                Arguments.of(
                        getLawData115(),
                        getLawEntity115()
                ),
                Arguments.of(
                        getLawData116(),
                        getLawEntity116()
                ),
                Arguments.of(
                        getLawData124P1(),
                        getLawEntity124P1()
                ),
                Arguments.of(
                        getLawData158(),
                        getLawEntity158()
                ),
                Arguments.of(
                        getLawData200P1(),
                        getLawEntity200P1()
                ),
                Arguments.of(
                        getLawData333(),
                        getLawEntity333()
                ),
                Arguments.of(
                        getLawData334(),
                        getLawEntity334()
                ),
                Arguments.of(
                        getLawData335(),
                        getLawEntity335()
                )
        );
    }

    private static Stream<Arguments> testLawEntityToLawDataMapping() {
        return Stream.of(
                Arguments.of(
                        getLawEntity105(),
                        getLawData105()
                ),
                Arguments.of(
                        getLawEntity106(),
                        getLawData106()
                ),
                Arguments.of(
                        getLawEntity114(),
                        getLawData114()
                ),
                Arguments.of(
                        getLawEntity115(),
                        getLawData115()
                ),
                Arguments.of(
                        getLawEntity116(),
                        getLawData116()
                ),
                Arguments.of(
                        getLawEntity124P1(),
                        getLawData124P1()
                ),
                Arguments.of(
                        getLawEntity158(),
                        getLawData158()
                ),
                Arguments.of(
                        getLawEntity200P1(),
                        getLawData200P1()
                ),
                Arguments.of(
                        getLawEntity333(),
                        getLawData333()
                ),
                Arguments.of(
                        getLawEntity334(),
                        getLawData334()
                ),
                Arguments.of(
                        getLawEntity335(),
                        getLawData335()
                )
        );
    }
}
