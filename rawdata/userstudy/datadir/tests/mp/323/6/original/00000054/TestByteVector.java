import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putByte(0x7F); // length=1, capacity=2

        byte[] src = new byte[] { 9, 8, 7, 6, 5 };
        bv.putByteArray(src, 1, 3); // copies 8,7,6 -> length=4 (enlarge)
        bv.putByteArray(null, 0, 2); // appends two zero bytes -> length=6

        assertEquals(6, bv.length);
        assertArrayEquals(new byte[] { 0x7F, 8, 7, 6, 0, 0 }, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}