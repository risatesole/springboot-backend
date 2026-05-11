package project.app.core.error;

/**
 * Thrown when a password contains no digit.
 */
public class PasswordMissingDigitException extends RuntimeException {

    public PasswordMissingDigitException() {
        super("Password must contain at least one digit");
    }
}