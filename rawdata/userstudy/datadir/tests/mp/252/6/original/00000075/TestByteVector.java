import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);

        // "A" (1 byte) + 'é' (2 bytes) + '€' (3 bytes) => 6 bytes, with 2-byte length prefix => total 8
        v.putUTF8("A\u00E9\u20AC");

        assertEquals(8, v.length);
        assertArrayEquals(new byte[] {0, 6, 65, (byte) 0xC3, (byte) 0xA9, (byte) 0xE2, (byte) 0x82, (byte) 0xAC},
                java.util.Arrays.copyOf(v.data, v.length));
    }
}