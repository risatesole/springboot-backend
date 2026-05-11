package project.app.core.error;

/**
 * Thrown when a password exceeds the maximum allowed length.
 */
public class PasswordTooLongException extends RuntimeException {

    public PasswordTooLongException(int maxLength) {
        super("Password cannot be longer than " + maxLength + " characters");
    }
}