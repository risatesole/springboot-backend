package project.app.core.user.value.birthdate;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Immutable value object representing a validated birth date.
 */
public class BirthDate {

    private final LocalDate value;

    public BirthDate(LocalDate value) {
        this.value = BirthDateValidator.validate(value);
    }

    public LocalDate getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof BirthDate)) return false;

        BirthDate birthDate = (BirthDate) o;

        return Objects.equals(value, birthDate.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
