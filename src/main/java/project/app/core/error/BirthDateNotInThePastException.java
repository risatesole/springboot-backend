package project.app.core.error;

/**
 * Thrown when a birth date is today or in the future.
 */
public class BirthDateNotInThePastException extends RuntimeException {

    public BirthDateNotInThePastException() {
        super("Birth date must be in the past");
    }
}
