package project.app.core.error;

public class DisplayNameTooLongException extends RuntimeException {

    public DisplayNameTooLongException(int maxLength) {
        super("Display name must be at most " + maxLength + " characters");
    }
}
