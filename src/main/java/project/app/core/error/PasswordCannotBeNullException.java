package project.app.core.error;

/**
 * Thrown when a password value is null.
 */
public class PasswordCannotBeNullException extends RuntimeException {

    public PasswordCannotBeNullException() {
        super("Password cannot be null");
    }
}