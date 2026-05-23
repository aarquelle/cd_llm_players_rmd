import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putUTF8("\u0000\u0080\u0800"); // UTF-8 bytes: 2 + 2 + 3 = 7, total with header = 9

        byte[] a = bv.data;
        assertArrayEquals(new byte[] {
                0x00, 0x07,
                (byte) 0xC0, (byte) 0x80,
                (byte) 0xC2, (byte) 0x80,
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80
        }, new byte[] { a[0], a[1], a[2], a[3], a[4], a[5], a[6], a[7], a[8] });

        assertEquals(9, bv.length);
    }
}