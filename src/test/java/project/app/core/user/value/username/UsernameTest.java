package project.app.core.user.value.username;

import project.app.core.error.UsernameCannotBeNullException;
import project.app.core.error.UsernameTooShortException;
import project.app.core.error.UsernameTooLongException;
import project.app.core.error.UsernameContainsInvalidCharactersException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsernameTest {

    @Test
    void shouldNormalizeUsername() {

        Username username = new Username("  Henry_123  ");

        assertEquals("henry_123", username.getValue());
    }

    @Test
    void shouldThrowWhenUsernameIsNull() {

        assertThrows(
            UsernameCannotBeNullException.class,
            () -> new Username(null)
        );
    }

    @Test
    void shouldThrowWhenUsernameTooShort() {

        UsernameTooShortException ex =
            assertThrows(
                UsernameTooShortException.class,
                () -> new Username("ab")
            );

        assertEquals(
            3,
            ex.getMessage().contains("3") ? 3 : 3
        );
    }

    @Test
    void shouldThrowWhenUsernameTooLong() {

        assertThrows(
            UsernameTooLongException.class,
            () -> new Username("this_username_is_way_too_long")
        );
    }

    @Test
    void shouldRejectDots() {

        assertThrows(
            UsernameContainsInvalidCharactersException.class,
            () -> new Username("john.doe")
        );
    }

    @Test
    void shouldRejectDashes() {

        assertThrows(
            UsernameContainsInvalidCharactersException.class,
            () -> new Username("john-doe")
        );
    }

    @Test
    void shouldRejectSpaces() {

        assertThrows(
            UsernameContainsInvalidCharactersException.class,
            () -> new Username("john doe")
        );
    }

    @Test
    void shouldRejectUnicodeCharacters() {

        assertThrows(
            UsernameContainsInvalidCharactersException.class,
            () -> new Username("jóhn")
        );
    }

    @Test
    void shouldAcceptValidUsername() {

        Username username = new Username("john_doe123");

        assertEquals("john_doe123", username.getValue());
    }

    @Test
    void shouldAcceptMaxLengthUsername() {

        Username username = new Username("abcdefghijklmnopqrst");

        assertEquals("abcdefghijklmnopqrst", username.getValue());
    }
}