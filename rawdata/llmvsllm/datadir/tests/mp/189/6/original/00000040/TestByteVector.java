import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putUTF8("A\u0800\u00A2");

        byte[] d = bv.data;
        int[] got = new int[] {
                bv.length,
                d[0] & 0xFF, d[1] & 0xFF,
                d[2] & 0xFF,
                d[3] & 0xFF, d[4] & 0xFF, d[5] & 0xFF,
                d[6] & 0xFF, d[7] & 0xFF
        };
        int[] expected = new int[] {
                8,
                0x00, 0x06,
                0x41,
                0xE0, 0xA0, 0x80,
                0xC2, 0xA2
        };
        assertArrayEquals(expected, got);
    }
}