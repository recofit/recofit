package site.recofit.ssafit.utility.common;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class StringUtility {
    public static String generateRandomString(final int length) {
        try {
            final Random random = SecureRandom.getInstanceStrong();

            return random.ints('0', 'z' + 1)
                    .filter(x -> (x <= '9' || x >= 'A') && (x <= 'Z' || x >= 'a'))
                    .limit(length)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
        } catch (final NoSuchAlgorithmException ex) {
            return null;
        }
    }
}
