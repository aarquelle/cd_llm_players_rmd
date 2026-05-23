import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3);
        bv.putByte(0x7F).putUTF8("A\u00E9\u20AC"); // 'A' (1), 'é' (2), '€' (3) => UTF8 byteLength = 6

        byte[] expected = new byte[] {
            (byte) 0x7F,
            0x00, 0x06,
            0x41,
            (byte) 0xC3, (byte) 0xA9,
            (byte) 0xE2, (byte) 0x82, (byte) 0xAC
        };

        assertEquals(10, bv.length);
        assertArrayEquals(expected, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}