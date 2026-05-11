package project.app.core.user.value.email;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import project.app.core.error.EmailCannotBeNullException;
import project.app.core.error.EmailTooLongException;
import project.app.core.error.EmailInvalidFormatException;

class EmailTest {

    // -------------------------
    // VALID CASES
    // -------------------------

    @Test
    void shouldCreateValidEmail() {

        Email email = new Email("Test@Email.com");

        assertEquals("test@email.com", email.getValue());
    }

    @Test
    void shouldTrimEmail() {

        Email email = new Email("   user@example.com   ");

        assertEquals("user@example.com", email.getValue());
    }

    @Test
    void shouldNormalizeToLowerCase() {

        Email email = new Email("USER@EXAMPLE.COM");

        assertEquals("user@example.com", email.getValue());
    }

    @Test
    void shouldConsiderTwoEmailsEqual() {

        Email e1 = new Email("User@Example.com");
        Email e2 = new Email("user@example.com");

        assertEquals(e1, e2);
        assertEquals(e1.hashCode(), e2.hashCode());
    }

    // -------------------------
    // NULL CASE
    // -------------------------

    @Test
    void shouldThrowWhenEmailIsNull() {

        assertThrows(
            EmailCannotBeNullException.class,
            () -> new Email(null)
        );
    }

    // -------------------------
    // FORMAT VALIDATION
    // -------------------------

    @Test
    void shouldThrowWhenEmailHasInvalidFormat_noAtSymbol() {

        assertThrows(
            EmailInvalidFormatException.class,
            () -> new Email("invalidemail.com")
        );
    }

    @Test
    void shouldThrowWhenEmailHasInvalidFormat_multipleAtSymbols() {

        assertThrows(
            EmailInvalidFormatException.class,
            () -> new Email("a@@example.com")
        );
    }

    @Test
    void shouldThrowWhenEmailHasInvalidCharacters() {

        assertThrows(
            EmailInvalidFormatException.class,
            () -> new Email("user!@example.com")
        );
    }

    // -------------------------
    // LENGTH VALIDATION
    // -------------------------

    @Test
    void shouldThrowWhenEmailTooLong() {

        String local = "a".repeat(250);
        String email = local + "@x.com"; // > 254 chars

        assertThrows(
            EmailTooLongException.class,
            () -> new Email(email)
        );
    }
}
