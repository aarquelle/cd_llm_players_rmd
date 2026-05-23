import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                String s = "A\u0000\u00E9\u0800"; // ASCII + NUL + 2-byte + 3-byte

        ByteVector bv = new ByteVector(2);
        bv.putUTF8(s);

        byte[] expected = new byte[] {
                0x00, 0x08,                 // modified UTF-8 byte length
                0x41,                       // 'A'
                (byte) 0xC0, (byte) 0x80,   // U+0000 encoded as 2 bytes
                (byte) 0xC3, (byte) 0xA9,   // U+00E9
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800
        };

        assertArrayEquals(expected, java.util.Arrays.copyOf(bv.data, bv.length));
        assertEquals(expected.length, bv.length);
    }
}