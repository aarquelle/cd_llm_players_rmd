import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                String s = "A\u00E9\u0800Z"; // 1 + 2 + 3 + 1 = 7 bytes
        ByteVector bv = new ByteVector(1);

        bv.putUTF8(s);

        byte[] expected = new byte[] {
                0x00, 0x07,
                0x41,
                (byte) 0xC3, (byte) 0xA9,
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80,
                0x5A
        };

        assertEquals(expected.length, bv.length);

        byte[] actual = new byte[] {
                bv.data[0], bv.data[1], bv.data[2], bv.data[3], bv.data[4],
                bv.data[5], bv.data[6], bv.data[7], bv.data[8]
        };
        assertArrayEquals(expected, actual);
    }
}