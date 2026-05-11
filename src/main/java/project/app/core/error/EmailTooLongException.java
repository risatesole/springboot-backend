package project.app.core.error;

/**
 * Thrown when an email exceeds the maximum allowed length.
 */
public class EmailTooLongException extends RuntimeException {

    public EmailTooLongException(int maxLength) {
        super("Email cannot be longer than " + maxLength + " characters");
    }
}
