package project.app.core.error;

/**
 * Thrown when an email value is null.
 */
public class EmailCannotBeNullException extends RuntimeException {

    public EmailCannotBeNullException() {
        super("Email cannot be null");
    }
}
