package project.app.core.user.value.email;

import project.app.core.error.EmailCannotBeNullException;
import project.app.core.error.EmailTooLongException;
import project.app.core.error.EmailInvalidFormatException;

/**
 * Handles validation rules for Email value object.
 */
final class EmailValidator {

    private static final int MAX_LENGTH = 254;

    // Simple RFC-like pattern (not perfect RFC, but practical for apps)
    private static final String EMAIL_PATTERN =
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    private EmailValidator() {}

    public static String validate(String value) {

        if (value == null) {
            throw new EmailCannotBeNullException();
        }

        String normalized = normalize(value);

        validateLength(normalized);
        validateFormat(normalized);

        return normalized;
    }

    private static String normalize(String value) {
        return value.trim().toLowerCase();
    }

    private static void validateLength(String value) {

        if (value.length() > MAX_LENGTH) {
            throw new EmailTooLongException(MAX_LENGTH);
        }
    }

    private static void validateFormat(String value) {

        if (!value.matches(EMAIL_PATTERN)) {
            throw new EmailInvalidFormatException();
        }
    }
}
