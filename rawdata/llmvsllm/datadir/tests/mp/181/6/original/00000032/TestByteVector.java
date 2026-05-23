import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putByte(0x7F); // prefix to ensure internal offset handling is correct

        String s = "A\u0080\u0800"; // 1-byte, 2-byte, 3-byte => total 6 bytes
        bv.putUTF8(s);

        assertEquals(1 + 2 + 6, bv.length);

        byte[] d = bv.data;
        assertArrayEquals(
            new byte[] {
                (byte) 0x7F, // prefix
                0x00, 0x06,  // UTF length = 6
                0x41,        // 'A'
                (byte) 0xC2, (byte) 0x80,             // U+0080
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80  // U+0800
            },
            new byte[] { d[0], d[1], d[2], d[3], d[4], d[5], d[6], d[7], d[8] }
        );
    }
}