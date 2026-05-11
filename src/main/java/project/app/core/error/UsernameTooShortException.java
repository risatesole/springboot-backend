package project.app.core.error;

public class UsernameTooShortException
    extends RuntimeException {

    public UsernameTooShortException(
        int minimumLength
    ) {
        super(
            "Username must be at least "
            + minimumLength
            + " characters long"
        );
    }
}
