import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(7); // force enlarge when adding 8 bytes
        bv.putByte(0xA5);
        bv.putLong(0x0123456789ABCDEFL);

        byte[] expected = new byte[] {
                (byte) 0xA5,
                0x01, 0x23, 0x45, 0x67,
                (byte) 0x89, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF
        };

        assertArrayEquals(expected, Arrays.copyOf(bv.data, bv.length));
        assertEquals(9, bv.length);
    }
}