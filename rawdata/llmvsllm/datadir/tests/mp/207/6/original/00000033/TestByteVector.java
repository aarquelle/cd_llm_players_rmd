import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        v.putUTF8("A\u20AC"); // 'A' (1 byte) + '€' (3 bytes) => UTF length 4

        assertArrayEquals(new byte[] {0, 4, 0x41, (byte) 0xE2, (byte) 0x82, (byte) 0xAC},
                java.util.Arrays.copyOf(v.data, v.length));
        assertEquals(6, v.length);
    }
}