import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putByte(0x7F); // sentinel prefix to ensure existing content is preserved during enlarge
        bv.putUTF8("\u00A2\u20AC"); // '¢' (2 bytes) + '€' (3 bytes) => UTF length 5

        assertEquals(1 + 2 + 5, bv.length);

        byte[] expected = new byte[] {
                (byte) 0x7F,
                0x00, 0x05,
                (byte) 0xC2, (byte) 0xA2,
                (byte) 0xE2, (byte) 0x82, (byte) 0xAC
        };
        assertArrayEquals(expected, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}