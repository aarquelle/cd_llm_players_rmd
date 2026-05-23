import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(16);
        bv.putUTF8("\u20AC"); // '€' -> E2 82 AC

        assertEquals(5, bv.length);
        byte[] actual = java.util.Arrays.copyOf(bv.data, bv.length);
        assertArrayEquals(new byte[]{0, 3, (byte) 0xE2, (byte) 0x82, (byte) 0xAC}, actual);
    }
}