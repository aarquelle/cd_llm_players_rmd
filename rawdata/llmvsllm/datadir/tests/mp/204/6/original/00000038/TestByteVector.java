import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        v.putByte(0x7F); // force enlarge when adding UTF8 payload

        String s = "\u0001\u0080\u0800"; // 1-byte, 2-byte, 3-byte UTF-8 sequences
        v.putUTF8(s);

        byte[] d = v.data;
        int off = 1;

        assertArrayEquals(new byte[] {
                0x7F,
                0x00, 0x06,              // UTF length = 6 bytes
                0x01,                    // U+0001
                (byte) 0xC2, (byte) 0x80, // U+0080
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800
        }, new byte[] { d[0], d[off], d[off+1], d[off+2], d[off+3], d[off+4], d[off+5], d[off+6], d[off+7] });

        assertEquals(1 + 2 + 6, v.length);
    }
}