import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.data[0] = (byte) 0x11;
        v.data[1] = (byte) 0x22; // garbage beyond logical length
        v.length = 1;

        v.putByteArray(new byte[] { 0x33 }, 0, 1); // length becomes 2 (still no enlarge)
        v.length = 1; // create a gap: capacity has garbage at index 1 but logical length is 1
        v.putByte(0x44); // length becomes 2, no enlarge yet
        v.putByte(0x55); // triggers enlarge; correct implementation copies only first 2 bytes (0x11,0x44)

        assertEquals((byte) 0x11, v.data[0]);
        assertEquals((byte) 0x44, v.data[1]);
    }
}