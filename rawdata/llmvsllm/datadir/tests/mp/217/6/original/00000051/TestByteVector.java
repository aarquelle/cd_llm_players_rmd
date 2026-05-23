import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);
        bv.putByte(0x55); // sentinel to ensure prefix rewrite uses correct base index
        bv.putUTF8("\u0080\u0800"); // 2-byte then 3-byte UTF-8 => 5 bytes payload

        assertEquals(1 + 2 + 5, bv.length);

        byte[] d = bv.data;
        assertArrayEquals(new byte[] {
                0x55,
                0x00, 0x05,                 // length prefix (5)
                (byte) 0xC2, (byte) 0x80,   // U+0080
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800
        }, new byte[] { d[0], d[1], d[2], d[3], d[4], d[5], d[6], d[7] });
    }
}