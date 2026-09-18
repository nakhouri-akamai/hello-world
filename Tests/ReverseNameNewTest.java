import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ReverseNameNewTest {

    @ParameterizedTest
    @MethodSource("generationYears")
    void returnsExpectedGenerationAtYearBoundaries(int year, String expectedGeneration) {
        assertEquals(expectedGeneration, ReverseNameNew.getGeneration(LocalDate.of(year, 1, 1)));
    }

    private static Stream<Arguments> generationYears() {
        return Stream.of(
                Arguments.of(1927, "Unknown Generation"),
                Arguments.of(1928, "Silent Generation"),
                Arguments.of(1945, "Silent Generation"),
                Arguments.of(1946, "Baby Boomer"),
                Arguments.of(1964, "Baby Boomer"),
                Arguments.of(1965, "Generation X"),
                Arguments.of(1980, "Generation X"),
                Arguments.of(1981, "Millennial"),
                Arguments.of(1996, "Millennial"),
                Arguments.of(1997, "Generation Z"),
                Arguments.of(2012, "Generation Z"),
                Arguments.of(2013, "Generation Alpha"),
                Arguments.of(2026, "Generation Alpha"));
    }

    @org.junit.jupiter.api.Test
    void returnsInvalidFormatForNullDate() {
        assertEquals("Invalid Date Format", ReverseNameNew.getGeneration(null));
    }
}