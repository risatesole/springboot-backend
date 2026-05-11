package project.app.core.user.value.username;

import java.util.Objects;

/**
 * Immutable value object representing a validated username.
 */
public class Username {

    private final String value;

    public Username(String value) {
        this.value = UsernameValidator.validate(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof Username)) return false;

        Username username = (Username) o;

        return Objects.equals(value, username.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}