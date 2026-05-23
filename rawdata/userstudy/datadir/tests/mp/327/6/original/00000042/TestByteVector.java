import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1); // force enlarge path
        long v = 0x0102030405060708L;

        bv.putLong(v);

        assertArrayEquals(new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }, java.util.Arrays.copyOf(bv.data, bv.length));
        assertEquals(8, bv.length);
    }
}