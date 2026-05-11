package project.app.core.error;

public class DisplayNameCannotBeBlankException extends RuntimeException {
    public DisplayNameCannotBeBlankException() {
        super("Display name cannot be blank");
    }
}