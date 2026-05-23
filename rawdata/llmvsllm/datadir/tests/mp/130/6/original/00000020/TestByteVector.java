import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        String s = "A\u0001\u0080\u0800"; // 1-byte, 1-byte, 2-bytes, 3-bytes => total 7 bytes

        bv.putUTF8(s);

        assertEquals(9, bv.length); // 2 (length prefix) + 7 (encoded)
        assertArrayEquals(new byte[] {
                0x00, 0x07,             // utf8 byte length
                0x41,                   // 'A'
                0x01,                   // U+0001
                (byte) 0xC2, (byte) 0x80, // U+0080
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800
        }, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}