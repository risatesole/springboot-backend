package project.app.core.user.value.password;

import java.util.Objects;

/**
 * Immutable value object representing a validated and hashed password.
 *
 * Accepts a raw plain-text password, validates it, then stores only the
 * BCrypt hash. The raw value is never retained after construction.
 */
public class Password {

    private final String hashedValue;

    public Password(String rawValue) {
        String validated = PasswordValidator.validate(rawValue);
        this.hashedValue = PasswordHasher.hash(validated);
    }

    /**
     * Used when rehydrating a Password from a persisted BCrypt hash
     * (e.g. loading from the database). No validation or hashing is applied.
     */
    public static Password fromHash(String existingHash) {
        if (existingHash == null) {
            throw new IllegalArgumentException("Existing hash cannot be null");
        }
        return new Password(existingHash, true);
    }

    /** Private constructor used by {@link #fromHash(String)}. */
    private Password(String value, boolean alreadyHashed) {
        this.hashedValue = alreadyHashed
                ? value
                : PasswordHasher.hash(PasswordValidator.validate(value));
    }

    public String getHashedValue() {
        return hashedValue;
    }

    /**
     * Verifies that a plain-text candidate matches this password's hash.
     */
    public boolean matches(String rawCandidate) {
        return PasswordHasher.matches(rawCandidate, hashedValue);
    }

    /**
     * Passwords are equal when their stored hashes are identical.
     * Two Password objects created from the same raw input will NOT be equal
     * because BCrypt generates a new salt each time — use {@link #matches}
     * for credential verification.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Password)) return false;
        Password other = (Password) o;
        return hashedValue != null && hashedValue.equals(other.hashedValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hashedValue);
    }

    /** Never exposes the hash in logs or error messages. */
    @Override
    public String toString() {
        return "[PROTECTED]";
    }
}