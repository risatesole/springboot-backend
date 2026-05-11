package project.app.core.error;

public class UsernameTooLongException
    extends RuntimeException {

    public UsernameTooLongException(
        int maximumLength
    ) {
        super(
            "Username cannot exceed "
            + maximumLength
            + " characters"
        );
    }
}
