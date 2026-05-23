import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putByte(0x7F); // shift internal "length" so putUTF8 must rewrite length bytes at non-zero offset

        String s = "A\u0800"; // 'A' (1 byte) + U+0800 (3 bytes) => byteLength = 4
        bv.putUTF8(s);

        byte[] d = bv.data;
        int off = 1; // after initial putByte
        assertArrayEquals(new byte[] { 0x7F, 0x00, 0x04, 0x41, (byte) 0xE0, (byte) 0xA0, (byte) 0x80 }, new byte[] {
                d[0], d[off], d[off + 1], d[off + 2], d[off + 3], d[off + 4], d[off + 5]
        });
        assertEquals(1 + 2 + 4, bv.length);
    }
}