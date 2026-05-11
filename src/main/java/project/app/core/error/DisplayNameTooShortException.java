package project.app.core.error;

public class DisplayNameTooShortException extends RuntimeException {

    public DisplayNameTooShortException(int minLength) {
        super("Display name must be at least " + minLength + " characters");
    }
}
