package project.app.core.user.value.displayname;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

import project.app.core.error.DisplayNameCannotBeNullException;
import project.app.core.error.DisplayNameCannotBeBlankException;
import project.app.core.error.DisplayNameTooShortException;
import project.app.core.error.DisplayNameTooLongException;

class DisplayNameTest {

    private static final String VALID_NAME = "John Doe";

    // -------------------------
    // VALID CASES
    // -------------------------

    @Test
    void shouldCreateValidDisplayName() {

        DisplayName name = new DisplayName(VALID_NAME);

        assertEquals("John Doe", name.getValue());
    }

    @Test
    void shouldNormalizeSpaces() {

        DisplayName name = new DisplayName("  John    Doe  ");

        assertEquals("John Doe", name.getValue());
    }

    @Test
    void shouldConsiderEqualDisplayNamesEqual() {

        DisplayName n1 = new DisplayName("John Doe");
        DisplayName n2 = new DisplayName("John Doe");

        assertEquals(n1, n2);
        assertEquals(n1.hashCode(), n2.hashCode());
    }

    @Test
    void shouldConsiderDifferentDisplayNamesNotEqual() {

        DisplayName n1 = new DisplayName("John Doe");
        DisplayName n2 = new DisplayName("Jane Doe");

        assertNotEquals(n1, n2);
    }

    @Test
    void shouldReturnCorrectToString() {

        DisplayName name = new DisplayName(VALID_NAME);

        assertEquals("John Doe", name.toString());
    }

    // -------------------------
    // NULL CASE
    // -------------------------

    @Test
    void shouldThrowWhenDisplayNameIsNull() {

        assertThrows(
            DisplayNameCannotBeNullException.class,
            () -> new DisplayName(null)
        );
    }

    // -------------------------
    // BLANK CASE
    // -------------------------

    @Test
    void shouldThrowWhenDisplayNameIsBlank() {

        assertThrows(
            DisplayNameCannotBeBlankException.class,
            () -> new DisplayName("   ")
        );
    }

    // -------------------------
    // LENGTH VALIDATION
    // -------------------------

    @Test
    void shouldThrowWhenDisplayNameTooShort() {

        assertThrows(
            DisplayNameTooShortException.class,
            () -> new DisplayName("Jo")
        );
    }

    @Test
    void shouldThrowWhenDisplayNameTooLong() {

        String longName = "ThisNameIsWayTooLongForTheSystem";

        assertThrows(
            DisplayNameTooLongException.class,
            () -> new DisplayName(longName)
        );
    }
}
