import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);
        String s = "A\u0000\u00A2\u20AC"; // 'A' (1), NUL (2), '¢' (2), '€' (3) => 8 bytes
        bv.putUTF8(s);

        byte[] expected = new byte[] {
                0x00, 0x08,
                0x41,
                (byte) 0xC0, (byte) 0x80,
                (byte) 0xC2, (byte) 0xA2,
                (byte) 0xE2, (byte) 0x82, (byte) 0xAC
        };

        assertEquals(expected.length, bv.length);
        assertArrayEquals(expected, Arrays.copyOf(bv.data, bv.length));
    }
}