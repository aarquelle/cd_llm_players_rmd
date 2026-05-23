import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                String s = "A\u0000\u00A2\u20ACZ";
        ByteVector bv = new ByteVector(1).putUTF8(s);

        byte[] utf8 = s.getBytes(StandardCharsets.UTF_8);
        byte[] expected = new byte[2 + utf8.length];
        expected[0] = (byte) (utf8.length >>> 8);
        expected[1] = (byte) utf8.length;
        assertEquals(utf8.length, ((expected[0] & 0xFF) << 8) | (expected[1] & 0xFF));

        byte[] actual = Arrays.copyOf(bv.data, bv.length);
        byte[] actualUtf8 = Arrays.copyOfRange(actual, 2, actual.length);
        assertArrayEquals(utf8, actualUtf8);
    }
}