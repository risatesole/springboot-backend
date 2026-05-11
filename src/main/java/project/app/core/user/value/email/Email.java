package project.app.core.user.value.email;

import java.util.Objects;

/**
 * Immutable value object representing a validated email.
 */
public class Email {

    private final String value;

    public Email(String value) {
        this.value = EmailValidator.validate(value);
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
        if (!(o instanceof Email)) return false;

        Email email = (Email) o;

        return Objects.equals(value, email.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
