import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);
        bv.putUTF8("\u0001\u0080\u0800"); // byteLength should be 1 + 2 + 3 = 6

        assertEquals(8, bv.length); // 2 length bytes + 6 utf8 bytes

        byte[] d = bv.data;
        assertArrayEquals(new byte[] {
                0, 6,          // UTF8 byte length
                0x01,          // U+0001 -> 01
                (byte) 0xC2, (byte) 0x80, // U+0080 -> C2 80
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800 -> E0 A0 80
        }, new byte[] { d[0], d[1], d[2], d[3], d[4], d[5], d[6], d[7] });
    }
}