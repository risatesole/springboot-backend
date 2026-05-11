package project.app.core.error;

/**
 * Thrown when a password contains no special character.
 */
public class PasswordMissingSpecialCharacterException extends RuntimeException {

    public PasswordMissingSpecialCharacterException() {
        super("Password must contain at least one special character (!@#$%^&* ...)");
    }
}