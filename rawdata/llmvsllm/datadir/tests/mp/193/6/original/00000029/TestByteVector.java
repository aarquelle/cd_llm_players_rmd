import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(4);
        bv.putUTF8("A\u0080\u0800B"); // 'A' (1), U+0080 (2), U+0800 (3), 'B' (1) => 7 bytes + 2 length bytes

        assertEquals(9, bv.length);

        byte[] d = bv.data;
        assertArrayEquals(new byte[] {
                0, 7,
                0x41,
                (byte) 0xC2, (byte) 0x80,
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80,
                0x42
        }, new byte[] { d[0], d[1], d[2], d[3], d[4], d[5], d[6], d[7], d[8] });
    }
}