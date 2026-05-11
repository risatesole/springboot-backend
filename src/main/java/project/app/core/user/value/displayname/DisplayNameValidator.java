package project.app.core.user.value.displayname;

import project.app.core.error.DisplayNameCannotBeNullException;
import project.app.core.error.DisplayNameCannotBeBlankException;
import project.app.core.error.DisplayNameTooShortException;
import project.app.core.error.DisplayNameTooLongException;

/**
 * Handles all validation rules for DisplayName.
 *
 * Rules:
 *  1. Not null
 *  2. Not blank
 *  3. Trimmed + normalized
 *  4. Min length: 3
 *  5. Max length: 30
 */
final class DisplayNameValidator {

    static final int MIN_LENGTH = 3;
    static final int MAX_LENGTH = 30;

    private DisplayNameValidator() {}

    public static String validate(String value) {

        if (value == null) {
            throw new DisplayNameCannotBeNullException();
        }

        String normalized = normalize(value);

        if (normalized.isBlank()) {
            throw new DisplayNameCannotBeBlankException();
        }

        if (normalized.length() < MIN_LENGTH) {
            throw new DisplayNameTooShortException(MIN_LENGTH);
        }

        if (normalized.length() > MAX_LENGTH) {
            throw new DisplayNameTooLongException(MAX_LENGTH);
        }

        return normalized;
    }

    private static String normalize(String value) {
        return value.trim().replaceAll("\\s+", " ");
    }
}
