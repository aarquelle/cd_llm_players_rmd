import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);

        v.putByte(0x7F);                 // fill initial capacity
        v.putUTF8("A\u0800");            // forces 3-byte UTF8 encoding for \u0800 and length header rewrite
        v.putByteArray(null, 0, 2);      // append two zero bytes

        byte[] expected = new byte[] {
                (byte) 0x7F,
                0x00, 0x04,              // UTF8 byte length = 1 ('A') + 3 ('\u0800') = 4
                0x41,
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80,
                0x00, 0x00
        };

        assertArrayEquals(expected, java.util.Arrays.copyOf(v.data, v.length));
        assertTrue(v.data.length >= v.length);
    }
}