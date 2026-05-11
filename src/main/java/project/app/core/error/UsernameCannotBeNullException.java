package project.app.core.error;

public class UsernameCannotBeNullException
    extends RuntimeException {

    public UsernameCannotBeNullException() {
        super("Username cannot be null");
    }
}
