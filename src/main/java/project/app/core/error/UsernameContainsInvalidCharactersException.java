package project.app.core.error;

public class UsernameContainsInvalidCharactersException
    extends RuntimeException {

    public UsernameContainsInvalidCharactersException() {
        super(
            "Username can only contain lowercase ASCII letters, numbers, and underscores"
        );
    }
}
