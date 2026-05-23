import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        v.putByte(0x7F); // ensure non-zero prefix and trigger enlarge when adding more
        byte[] src = new byte[] { 9, 8, 7, 6, 5 };
        v.putByteArray(src, 1, 3); // copy {8,7,6} after 0x7F

        assertArrayEquals(new byte[] { (byte) 0x7F, 8, 7, 6 }, java.util.Arrays.copyOf(v.data, v.length));
        assertEquals(4, v.length);
    }
}