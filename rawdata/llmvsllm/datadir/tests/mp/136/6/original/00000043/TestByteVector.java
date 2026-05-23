import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putByte(0x7F); // shift internal length, force enlarge inside putUTF8
        bv.putUTF8("\u0000\u0080\u0800"); // forces slow path: 2 + 2 + 3 = 7 bytes of UTF-8

        byte[] data = bv.data;

        assertEquals(10, bv.length); // 1 + 2(prefix) + 7(encoded)
        assertArrayEquals(new byte[] {
                (byte) 0x7F,
                0x00, 0x07,
                (byte) 0xC0, (byte) 0x80,
                (byte) 0xC2, (byte) 0x80,
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80
        }, new byte[] {
                data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9]
        });
    }
}