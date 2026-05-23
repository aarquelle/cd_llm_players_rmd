import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);

        v.putByteArray(null, 0, 1); // must enlarge and advance length by 1 without copying

        byte[] src = new byte[] { 9, 8, 7, 6, 5 };
        v.putByteArray(src, 2, 3); // copy {7,6,5} after the first byte

        assertArrayEquals(new byte[] { 0, 7, 6, 5 }, java.util.Arrays.copyOf(v.data, v.length));
        assertEquals(4, v.length);
    }
}