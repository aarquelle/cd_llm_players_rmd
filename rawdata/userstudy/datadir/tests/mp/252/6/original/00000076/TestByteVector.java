import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putByte(0x7F);
        bv.putUTF8("\u0000\u0080\u0800");

        assertEquals(1 + 2 + 7, bv.length);

        byte[] d = bv.data;
        int o = 1;
        assertArrayEquals(new byte[] {
                0x00, 0x07,                 // UTF length = 7 bytes
                (byte) 0xC0, (byte) 0x80,    // U+0000
                (byte) 0xC2, (byte) 0x80,    // U+0080
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800
        }, new byte[] { d[o], d[o+1], d[o+2], d[o+3], d[o+4], d[o+5], d[o+6], d[o+7], d[o+8] });
    }
}