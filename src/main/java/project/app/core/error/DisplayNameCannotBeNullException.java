package project.app.core.error;

public class DisplayNameCannotBeNullException extends RuntimeException {
    public DisplayNameCannotBeNullException() {
        super("Display name cannot be null");
    }
}
