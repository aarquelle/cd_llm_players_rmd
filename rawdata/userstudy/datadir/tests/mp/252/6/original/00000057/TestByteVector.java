import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(4);
        bv.putUTF8("A\u0080\u0800"); // forces general UTF8 path: 1 + 2 + 3 bytes
        bv.putLong(0x0102030405060708L);

        byte[] expected = new byte[] {
                0x00, 0x06, // UTF8 byte length = 6
                0x41,       // 'A'
                (byte) 0xC2, (byte) 0x80,             // U+0080
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80, // U+0800
                0x01, 0x02, 0x03, 0x04,
                0x05, 0x06, 0x07, 0x08
        };

        assertEquals(16, bv.length);
        assertArrayEquals(expected, bv.data);
    }
}