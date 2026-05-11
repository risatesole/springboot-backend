package project.app.core.error;

/**
 * Thrown when a birth date value is null.
 */
public class BirthDateCannotBeNullException extends RuntimeException {

    public BirthDateCannotBeNullException() {
        super("Birth date cannot be null");
    }
}
