package project.app.core.user.value;

import project.app.core.error.UsernameCannotBeNullException;
import project.app.core.error.UsernameTooShortException;
import project.app.core.error.UsernameTooLongException;
import project.app.core.error.UsernameContainsInvalidCharactersException;

/**
 * Handles validation rules for Username value object.
 */
final class UsernameValidator {

    private static final int MIN_LENGTH = 3;
    private static final int MAX_LENGTH = 20;

    private static final String USERNAME_PATTERN =
        "^[a-z0-9_]+$";

    private UsernameValidator() {}

    public static String validate(String value) {

        if (value == null) {
            throw new UsernameCannotBeNullException();
        }

        String normalized = normalize(value);

        validateLength(normalized);
        validateCharacters(normalized);

        return normalized;
    }

    private static String normalize(String value) {
        return value.trim().toLowerCase();
    }

    private static void validateLength(String value) {

        if (value.length() < MIN_LENGTH) {
            throw new UsernameTooShortException(MIN_LENGTH);
        }

        if (value.length() > MAX_LENGTH) {
            throw new UsernameTooLongException(MAX_LENGTH);
        }
    }

    private static void validateCharacters(String value) {

        if (!value.matches(USERNAME_PATTERN)) {
            throw new UsernameContainsInvalidCharactersException();
        }
    }
}