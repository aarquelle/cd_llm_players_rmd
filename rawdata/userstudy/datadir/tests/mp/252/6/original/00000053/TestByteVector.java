import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3);
        bv.putUTF8("A\u00E9\u20AC"); // A + é (2 bytes) + € (3 bytes) => utf8 length 6
        bv.putInt(0x01020304);

        byte[] expected = new byte[] {
                0x00, 0x06,                         // UTF8 byte length (big-endian)
                0x41,                               // 'A'
                (byte) 0xC3, (byte) 0xA9,           // 'é'
                (byte) 0xE2, (byte) 0x82, (byte) 0xAC, // '€'
                0x01, 0x02, 0x03, 0x04              // int big-endian
        };

        assertEquals(expected.length, bv.length);
        assertArrayEquals(expected, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}