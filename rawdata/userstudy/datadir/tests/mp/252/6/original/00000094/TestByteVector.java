import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(4);
        bv.putByte(0x7F).putUTF8("A\u00A2\u20AC"); // 'A' (1), '¢' (2), '€' (3) => 6 bytes

        assertEquals(9, bv.length); // 1 + 2 + 6
        assertArrayEquals(new byte[] {
                0x7F,
                0x00, 0x06,
                0x41,
                (byte) 0xC2, (byte) 0xA2,
                (byte) 0xE2, (byte) 0x82, (byte) 0xAC
        }, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}