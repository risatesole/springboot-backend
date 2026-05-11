package project.app.core.error;

/**
 * Thrown when an email does not match the required format.
 */
public class EmailInvalidFormatException extends RuntimeException {

    public EmailInvalidFormatException() {
        super("Email format is invalid");
    }
}
