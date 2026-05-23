import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        v.putByte(0x55);

        byte[] src = new byte[] { 1, 2, 3, 4 };
        v.putByteArray(src, 1, 2); // copies 2,3
        v.putByteArray(null, 0, 3); // adds three zero bytes without copying

        assertEquals(6, v.length);
        assertArrayEquals(new byte[] { (byte) 0x55, 2, 3, 0, 0, 0 }, java.util.Arrays.copyOf(v.data, v.length));
    }
}