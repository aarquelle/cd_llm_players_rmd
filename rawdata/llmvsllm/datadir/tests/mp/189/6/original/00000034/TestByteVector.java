import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.putByte(0x55);

        byte[] src = new byte[] { (byte) 0x10, (byte) 0x11, (byte) 0x12, (byte) 0x13, (byte) 0x14 };
        v.putByteArray(src, 1, 3); // copy 0x11,0x12,0x13

        assertEquals(4, v.length);
        assertArrayEquals(new byte[] { (byte) 0x55, (byte) 0x11, (byte) 0x12, (byte) 0x13 }, java.util.Arrays.copyOf(v.data, v.length));
    }
}