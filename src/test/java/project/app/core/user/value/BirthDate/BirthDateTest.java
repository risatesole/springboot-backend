package project.app.core.user.value.birthdate;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import project.app.core.error.BirthDateCannotBeNullException;
import project.app.core.error.BirthDateNotInThePastException;
import project.app.core.error.BirthDateExceedsMaxAgeException;

import java.time.LocalDate;

class BirthDateTest {

    private static final LocalDate VALID_DATE = LocalDate.now().minusYears(25);

    // -------------------------
    // VALID CASES
    // -------------------------

    @Test
    void shouldCreateValidBirthDate() {

        BirthDate birthDate = new BirthDate(VALID_DATE);

        assertEquals(VALID_DATE, birthDate.getValue());
    }

    @Test
    void shouldCreateBirthDateOneDayInThePast() {

        LocalDate yesterday = LocalDate.now().minusDays(1);

        assertDoesNotThrow(() -> new BirthDate(yesterday));
    }

    @Test
    void shouldCreateBirthDateAtMaxAgeLimit() {

        // Exactly MAX_AGE years ago today is still valid
        LocalDate exactLimit = LocalDate.now().minusYears(BirthDateValidator.MAX_AGE);

        assertDoesNotThrow(() -> new BirthDate(exactLimit));
    }

    @Test
    void shouldConsiderTwoBirthDatesWithSameValueEqual() {

        BirthDate b1 = new BirthDate(VALID_DATE);
        BirthDate b2 = new BirthDate(VALID_DATE);

        assertEquals(b1, b2);
        assertEquals(b1.hashCode(), b2.hashCode());
    }

    @Test
    void shouldConsiderTwoBirthDatesWithDifferentValuesNotEqual() {

        BirthDate b1 = new BirthDate(VALID_DATE);
        BirthDate b2 = new BirthDate(VALID_DATE.minusDays(1));

        assertNotEquals(b1, b2);
    }

    @Test
    void shouldReturnCorrectToString() {

        BirthDate birthDate = new BirthDate(VALID_DATE);

        assertEquals(VALID_DATE.toString(), birthDate.toString());
    }

    // -------------------------
    // NULL CASE
    // -------------------------

    @Test
    void shouldThrowWhenBirthDateIsNull() {

        assertThrows(
            BirthDateCannotBeNullException.class,
            () -> new BirthDate(null)
        );
    }

    // -------------------------
    // PAST VALIDATION
    // -------------------------

    @Test
    void shouldThrowWhenBirthDateIsToday() {

        assertThrows(
            BirthDateNotInThePastException.class,
            () -> new BirthDate(LocalDate.now())
        );
    }

    @Test
    void shouldThrowWhenBirthDateIsInTheFuture() {

        assertThrows(
            BirthDateNotInThePastException.class,
            () -> new BirthDate(LocalDate.now().plusDays(1))
        );
    }

    @Test
    void shouldThrowWhenBirthDateIsFarInTheFuture() {

        assertThrows(
            BirthDateNotInThePastException.class,
            () -> new BirthDate(LocalDate.now().plusYears(10))
        );
    }

    // -------------------------
    // MAX AGE VALIDATION
    // -------------------------

    @Test
    void shouldThrowWhenBirthDateExceedsMaxAge() {

        LocalDate tooOld = LocalDate.now().minusYears(BirthDateValidator.MAX_AGE).minusDays(1);

        assertThrows(
            BirthDateExceedsMaxAgeException.class,
            () -> new BirthDate(tooOld)
        );
    }

    @Test
    void shouldThrowWhenBirthDateIsFarBeyondMaxAge() {

        assertThrows(
            BirthDateExceedsMaxAgeException.class,
            () -> new BirthDate(LocalDate.of(1800, 1, 1))
        );
    }
}
