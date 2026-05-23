import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        v.putByte(0x7F);

        byte[] src = new byte[] { 0x10, 0x11, 0x12, 0x13, 0x14 };
        v.putByteArray(src, 1, 3); // copy 0x11,0x12,0x13 after existing 0x7F

        assertArrayEquals(new byte[] { 0x7F, 0x11, 0x12, 0x13 }, java.util.Arrays.copyOf(v.data, v.length));
        assertTrue(v.data.length >= 4);
    }
}