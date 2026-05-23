import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);
        bv.putUTF8("\u0080\u0800").putLong(0x0102030405060708L);

        byte[] expected = new byte[] {
                0x00, 0x05,             // UTF8 byte length = 5
                (byte) 0xC2, (byte) 0x80, // U+0080 -> 2 bytes
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80, // U+0800 -> 3 bytes
                0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08 // long big-endian
        };

        assertEquals(expected.length, bv.length);
        assertArrayEquals(expected, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}