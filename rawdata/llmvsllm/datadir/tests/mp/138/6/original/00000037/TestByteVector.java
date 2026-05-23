import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                String s = "A\u0000\u00A2\u0800B"; // ASCII + NUL + 2-byte + 3-byte + ASCII

        ByteVector bv = new ByteVector(1); // force enlargement
        bv.putUTF8(s);

        byte[] expected = new byte[] {
                0x00, 0x09,               // modified UTF-8 byte length = 9
                0x41,                     // 'A'
                (byte) 0xC0, (byte) 0x80,  // U+0000
                (byte) 0xC2, (byte) 0xA2,  // U+00A2
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80, // U+0800
                0x42                      // 'B'
        };

        assertArrayEquals(expected, java.util.Arrays.copyOf(bv.data, bv.length));
        assertEquals(expected.length, bv.length);
    }
}