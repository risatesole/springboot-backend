package project.app.core.error;

/**
 * Thrown when a password contains no uppercase letter.
 */
public class PasswordMissingUppercaseException extends RuntimeException {

    public PasswordMissingUppercaseException() {
        super("Password must contain at least one uppercase letter");
    }
}