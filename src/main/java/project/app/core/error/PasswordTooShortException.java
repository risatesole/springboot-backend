package project.app.core.error;

/**
 * Thrown when a password is shorter than the minimum required length.
 */
public class PasswordTooShortException extends RuntimeException {

    public PasswordTooShortException(int minLength) {
        super("Password must be at least " + minLength + " characters long");
    }
}
