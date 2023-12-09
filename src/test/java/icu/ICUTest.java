package icu;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ibm.icu.text.NumberFormat;
import com.ibm.icu.text.RuleBasedNumberFormat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.text.ParseException;
import java.util.Locale;
import java.util.stream.Stream;

@DisplayName("ICU library tests")
public class ICUTest {
    private final NumberFormat format = new RuleBasedNumberFormat(Locale.forLanguageTag("ru"), RuleBasedNumberFormat.SPELLOUT);

    @ParameterizedTest
    @MethodSource("parseTestArguments")
    @DisplayName("Convert text to number")
    public void parseTest(String text, double expected) throws ParseException {
        Number actual = format.parse(text);
        assertEquals(expected, actual.doubleValue());
    }

    @ParameterizedTest
    @MethodSource("formatTestArguments")
    @DisplayName("Convert number to text")
    public void formatTest(int number, String expected) {
        String actual = format.format(number);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> parseTestArguments() {
        return Stream.of(
                Arguments.of("шесть", 6),
                Arguments.of("десять", 10),
                Arguments.of("пяти", 5),
                Arguments.of("пятнадцати", 15),
                Arguments.of("одного", 1),
                Arguments.of("ста тысяч", 100000),
                Arguments.of("десятикратной", 10),
                Arguments.of("пятнадцатикратной", 15),
                Arguments.of("один целых пять десятых", 1.5)
        );
    }

    private static Stream<Arguments> formatTestArguments() {
        return Stream.of(
                Arguments.of(5, "пять"),
                Arguments.of(7, "семь"),
                Arguments.of(12, "двенадцать"),
                Arguments.of(23, "двадцать три"),
                Arguments.of(500000, "пятьсот тысяч")
        );
    }
}
