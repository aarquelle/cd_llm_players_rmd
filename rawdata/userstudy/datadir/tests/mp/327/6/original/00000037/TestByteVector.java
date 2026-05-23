import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(7);
        long v = 0x0123456789ABCDEFL;

        bv.putLong(v);

        assertEquals(8, bv.length);
        assertArrayEquals(
                new byte[] { 0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF },
                java.util.Arrays.copyOf(bv.data, bv.length)
        );
    }
}