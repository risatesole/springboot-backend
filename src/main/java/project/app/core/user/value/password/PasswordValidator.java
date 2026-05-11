package project.app.core.user.value.password;

import project.app.core.error.PasswordCannotBeNullException;
import project.app.core.error.PasswordTooShortException;
import project.app.core.error.PasswordTooLongException;
import project.app.core.error.PasswordMissingUppercaseException;
import project.app.core.error.PasswordMissingDigitException;
import project.app.core.error.PasswordMissingSpecialCharacterException;

/**
 * Handles all validation rules for the Password value object.
 *
 * Rules applied (in order):
 *   1. Not null
 *   2. Minimum length  (8 characters)
 *   3. Maximum length  (72 characters — BCrypt hard limit)
 *   4. At least one uppercase letter
 *   5. At least one digit
 *   6. At least one special character  (!@#$%^&*()-_=+[]{};:'",.<>?/\\|`~)
 */
final class PasswordValidator {

    static final int MIN_LENGTH = 8;
    static final int MAX_LENGTH = 72; // BCrypt silently ignores chars beyond 72

    private static final String SPECIAL_CHARS = "!@#$%^&*()-_=+[]{}|;:'\",.<>?/\\`~";
    private static final String HAS_UPPERCASE    = ".*[A-Z].*";
    private static final String HAS_DIGIT        = ".*[0-9].*";

    private PasswordValidator() {}

    public static String validate(String value) {

        if (value == null) {
            throw new PasswordCannotBeNullException();
        }

        validateLength(value);
        validateUppercase(value);
        validateDigit(value);
        validateSpecialCharacter(value);

        return value;
    }

    private static void validateLength(String value) {

        if (value.length() < MIN_LENGTH) {
            throw new PasswordTooShortException(MIN_LENGTH);
        }

        if (value.length() > MAX_LENGTH) {
            throw new PasswordTooLongException(MAX_LENGTH);
        }
    }

    private static void validateUppercase(String value) {

        if (!value.matches(HAS_UPPERCASE)) {
            throw new PasswordMissingUppercaseException();
        }
    }

    private static void validateDigit(String value) {

        if (!value.matches(HAS_DIGIT)) {
            throw new PasswordMissingDigitException();
        }
    }

    private static void validateSpecialCharacter(String value) {

        boolean found = false;

        for (char c : value.toCharArray()) {
            if (SPECIAL_CHARS.indexOf(c) >= 0) {
                found = true;
                break;
            }
        }

        if (!found) {
            throw new PasswordMissingSpecialCharacterException();
        }
    }
}
