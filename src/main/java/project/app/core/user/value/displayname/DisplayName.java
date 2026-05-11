package project.app.core.user.value.displayname;

import java.util.Objects;

/**
 * Immutable value object representing a validated display name.
 */
public class DisplayName {

    private final String value;

    public DisplayName(String value) {
        this.value = DisplayNameValidator.validate(value);
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
        if (!(o instanceof DisplayName)) return false;

        DisplayName that = (DisplayName) o;

        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
