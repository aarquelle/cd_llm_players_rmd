import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(7); // force enlarge path
        long l = 0x0123456789ABCDEFL;

        v.putLong(l);

        assertArrayEquals(new byte[] {
                0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF
        }, java.util.Arrays.copyOf(v.data, 8));
        assertEquals(8, v.length);
    }
}