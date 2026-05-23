import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putUTF8("\u0080\u0800"); // U+0080 -> 2 bytes, U+0800 -> 3 bytes; total 5 bytes

        assertEquals(7, bv.length); // 2 length bytes + 5 payload bytes

        byte[] d = bv.data;
        assertArrayEquals(
                new byte[] {
                        0x00, 0x05,                 // UTF8 byte length = 5
                        (byte) 0xC2, (byte) 0x80,    // U+0080
                        (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800
                },
                new byte[] { d[0], d[1], d[2], d[3], d[4], d[5], d[6] }
        );
    }
}