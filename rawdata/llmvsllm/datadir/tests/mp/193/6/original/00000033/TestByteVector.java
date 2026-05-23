import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);

        v.putByte(0x7F);
        v.putUTF8("A\u0000\u0080\u0800");
        v.putLong(0x0102030405060708L);

        byte[] expected = new byte[] {
            (byte) 0x7F,

            0x00, 0x08,                 // UTF8 byte length = 8
            0x41,                       // 'A'
            (byte) 0xC0, (byte) 0x80,    // U+0000
            (byte) 0xC2, (byte) 0x80,    // U+0080
            (byte) 0xE0, (byte) 0xA0, (byte) 0x80, // U+0800

            0x01, 0x02, 0x03, 0x04,
            0x05, 0x06, 0x07, 0x08
        };

        assertArrayEquals(expected, java.util.Arrays.copyOf(v.data, v.length));
        assertTrue(v.data.length >= v.length);
    }
}