import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(8);
        bv.putLong(0x1122334455667788L).putLong(0x99AABBCCDDEEFF00L);

        assertEquals(16, bv.length);

        byte[] expected = new byte[] {
            0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77, (byte) 0x88,
            (byte) 0x99, (byte) 0xAA, (byte) 0xBB, (byte) 0xCC, (byte) 0xDD, (byte) 0xEE, (byte) 0xFF, 0x00
        };
        assertTrue(Arrays.equals(expected, Arrays.copyOf(bv.data, bv.length)));
    }
}