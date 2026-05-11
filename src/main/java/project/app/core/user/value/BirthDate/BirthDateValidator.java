package project.app.core.user.value.birthdate;

import project.app.core.error.BirthDateCannotBeNullException;
import project.app.core.error.BirthDateNotInThePastException;
import project.app.core.error.BirthDateExceedsMaxAgeException;

import java.time.LocalDate;

/**
 * Handles all validation rules for the BirthDate value object.
 *
 * Rules applied (in order):
 *   1. Not null
 *   2. Must be in the past (not today, not a future date)
 *   3. Must not imply an age older than MAX_AGE (120 years)
 */
final class BirthDateValidator {

    static final int MAX_AGE = 120;

    private BirthDateValidator() {}

    public static LocalDate validate(LocalDate value) {

        if (value == null) {
            throw new BirthDateCannotBeNullException();
        }

        LocalDate today = LocalDate.now();

        validateIsInThePast(value, today);
        validateMaxAge(value, today);

        return value;
    }

    private static void validateIsInThePast(LocalDate value, LocalDate today) {

        if (!value.isBefore(today)) {
            throw new BirthDateNotInThePastException();
        }
    }

    private static void validateMaxAge(LocalDate value, LocalDate today) {

        LocalDate earliest = today.minusYears(MAX_AGE);

        if (value.isBefore(earliest)) {
            throw new BirthDateExceedsMaxAgeException(MAX_AGE);
        }
    }
}
