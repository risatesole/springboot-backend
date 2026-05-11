package project.app.core.user.value.password;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

/**
 * Password hashing and verification using PBKDF2WithHmacSHA256.
 *
 * Stored format:  base64(salt) + ":" + base64(hash)
 *
 * Parameters:
 *   - Algorithm : PBKDF2WithHmacSHA256
 *   - Iterations: 310_000  (NIST SP 800-132 recommendation for SHA-256)
 *   - Salt size : 16 bytes (128 bits)
 *   - Hash size : 32 bytes (256 bits)
 */
final class PasswordHasher {

    private static final String ALGORITHM  = "PBKDF2WithHmacSHA256";
    private static final int    ITERATIONS = 310_000;
    private static final int    SALT_BYTES = 16;
    private static final int    HASH_BITS  = 256;
    private static final String DELIMITER  = ":";

    private PasswordHasher() {}

    public static String hash(String rawPassword) {

        byte[] salt = generateSalt();
        byte[] hash = pbkdf2(rawPassword.toCharArray(), salt);

        return Base64.getEncoder().encodeToString(salt)
                + DELIMITER
                + Base64.getEncoder().encodeToString(hash);
    }

    public static boolean matches(String rawCandidate, String storedValue) {

        if (rawCandidate == null || storedValue == null) {
            return false;
        }

        String[] parts = storedValue.split(DELIMITER, 2);

        if (parts.length != 2) {
            return false;
        }

        byte[] salt         = Base64.getDecoder().decode(parts[0]);
        byte[] expectedHash = Base64.getDecoder().decode(parts[1]);
        byte[] actualHash   = pbkdf2(rawCandidate.toCharArray(), salt);

        return constantTimeEquals(expectedHash, actualHash);
    }

    // ------------------------------------------------------------------
    // Private helpers
    // ------------------------------------------------------------------

    private static byte[] generateSalt() {
        byte[] salt = new byte[SALT_BYTES];
        new SecureRandom().nextBytes(salt);
        return salt;
    }

    private static byte[] pbkdf2(char[] password, byte[] salt) {
        try {
            PBEKeySpec       spec    = new PBEKeySpec(password, salt, ITERATIONS, HASH_BITS);
            SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
            byte[]           hash    = factory.generateSecret(spec).getEncoded();
            spec.clearPassword();
            return hash;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new IllegalStateException("Password hashing failed", e);
        }
    }

    /** Timing-safe byte comparison — prevents timing attacks. */
    private static boolean constantTimeEquals(byte[] a, byte[] b) {

        if (a.length != b.length) {
            return false;
        }

        int diff = 0;

        for (int i = 0; i < a.length; i++) {
            diff |= a[i] ^ b[i];
        }

        return diff == 0;
    }
}
