package project.app.core.error;

/**
 * Thrown when a birth date implies an age greater than the allowed maximum.
 */
public class BirthDateExceedsMaxAgeException extends RuntimeException {

    public BirthDateExceedsMaxAgeException(int maxAge) {
        super("Birth date implies an age greater than the maximum allowed (" + maxAge + " years)");
    }
}
