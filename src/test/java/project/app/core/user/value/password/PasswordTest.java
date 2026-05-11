package project.app.core.user.value.password;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import project.app.core.error.PasswordCannotBeNullException;
import project.app.core.error.PasswordTooShortException;
import project.app.core.error.PasswordTooLongException;
import project.app.core.error.PasswordMissingUppercaseException;
import project.app.core.error.PasswordMissingDigitException;
import project.app.core.error.PasswordMissingSpecialCharacterException;

class PasswordTest {

    private static final String VALID_RAW = "Secret1!";

    // -------------------------
    // VALID CASES
    // -------------------------

    @Test
    void shouldCreateValidPassword() {

        Password password = new Password(VALID_RAW);

        assertNotNull(password.getHashedValue());
    }

    @Test
    void shouldHashPassword() {

        Password password = new Password(VALID_RAW);

        assertNotEquals(VALID_RAW, password.getHashedValue());
    }

    @Test
    void shouldGenerateDifferentHashesForSameInput() {

        // BCrypt uses a random salt each time
        Password p1 = new Password(VALID_RAW);
        Password p2 = new Password(VALID_RAW);

        assertNotEquals(p1.getHashedValue(), p2.getHashedValue());
    }

    @Test
    void shouldMatchCorrectRawPassword() {

        Password password = new Password(VALID_RAW);

        assertTrue(password.matches(VALID_RAW));
    }

    @Test
    void shouldNotMatchWrongRawPassword() {

        Password password = new Password(VALID_RAW);

        assertFalse(password.matches("Wrong1!"));
    }

    @Test
    void shouldNotMatchNullCandidate() {

        Password password = new Password(VALID_RAW);

        assertFalse(password.matches(null));
    }

    @Test
    void shouldRehydrateFromExistingHash() {

        Password original = new Password(VALID_RAW);
        Password rehydrated = Password.fromHash(original.getHashedValue());

        assertEquals(original, rehydrated);
        assertTrue(rehydrated.matches(VALID_RAW));
    }

    @Test
    void shouldHideValueInToString() {

        Password password = new Password(VALID_RAW);

        assertEquals("[PROTECTED]", password.toString());
    }

    // -------------------------
    // NULL CASE
    // -------------------------

    @Test
    void shouldThrowWhenPasswordIsNull() {

        assertThrows(
            PasswordCannotBeNullException.class,
            () -> new Password(null)
        );
    }

    // -------------------------
    // LENGTH VALIDATION
    // -------------------------

    @Test
    void shouldThrowWhenPasswordIsTooShort() {

        assertThrows(
            PasswordTooShortException.class,
            () -> new Password("Ab1!")      // 4 chars, below MIN 8
        );
    }

    @Test
    void shouldThrowWhenPasswordIsTooLong() {

        String tooLong = "A1!" + "a".repeat(PasswordValidator.MAX_LENGTH); // > 72

        assertThrows(
            PasswordTooLongException.class,
            () -> new Password(tooLong)
        );
    }

    @Test
    void shouldAcceptPasswordAtMinLength() {

        // exactly MIN_LENGTH chars: 6 lowercase + 1 upper + 1 digit + special covered
        String atMin = "Abcde1!"; // 7 chars — adjust to MIN_LENGTH if you change it
        // Build exactly MIN_LENGTH chars that satisfy all rules
        String minValid = "A1!" + "a".repeat(PasswordValidator.MIN_LENGTH - 3);

        assertDoesNotThrow(() -> new Password(minValid));
    }

    @Test
    void shouldAcceptPasswordAtMaxLength() {

        String atMax = "A1!" + "a".repeat(PasswordValidator.MAX_LENGTH - 3);

        assertDoesNotThrow(() -> new Password(atMax));
    }

    // -------------------------
    // UPPERCASE VALIDATION
    // -------------------------

    @Test
    void shouldThrowWhenPasswordMissingUppercase() {

        assertThrows(
            PasswordMissingUppercaseException.class,
            () -> new Password("secret1!")
        );
    }

    // -------------------------
    // DIGIT VALIDATION
    // -------------------------

    @Test
    void shouldThrowWhenPasswordMissingDigit() {

        assertThrows(
            PasswordMissingDigitException.class,
            () -> new Password("SecretABC!")
        );
    }

    // -------------------------
    // SPECIAL CHARACTER VALIDATION
    // -------------------------

    @Test
    void shouldThrowWhenPasswordMissingSpecialCharacter() {

        assertThrows(
            PasswordMissingSpecialCharacterException.class,
            () -> new Password("Secret123")
        );
    }

    @Test
    void shouldAcceptVariousSpecialCharacters() {

        String[] validPasswords = {
            "Secret1@",
            "Secret1#",
            "Secret1$",
            "Secret1%",
            "Secret1!",
            "Secret1?",
            "Secret1.",
        };

        for (String raw : validPasswords) {
            assertDoesNotThrow(() -> new Password(raw), "Should accept: " + raw);
        }
    }

    // -------------------------
    // EQUALITY
    // -------------------------

    @Test
    void shouldBeEqualWhenRehydratedFromSameHash() {

        Password p = new Password(VALID_RAW);
        Password rehydrated = Password.fromHash(p.getHashedValue());

        assertEquals(p, rehydrated);
        assertEquals(p.hashCode(), rehydrated.hashCode());
    }

    @Test
    void shouldNotBeEqualForTwoFreshPasswordsWithSameInput() {

        // Two independent constructions → different salts → different hashes
        Password p1 = new Password(VALID_RAW);
        Password p2 = new Password(VALID_RAW);

        assertNotEquals(p1, p2);
    }
}
