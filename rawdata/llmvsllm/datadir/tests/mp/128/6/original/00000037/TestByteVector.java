import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        v.putByte(0x7F);

        byte[] src = new byte[] { 0x00, 0x11, 0x22, 0x33, 0x44 };
        v.putByteArray(src, 1, 3); // should copy 0x11,0x22,0x33

        assertArrayEquals(new byte[] { (byte) 0x7F, 0x11, 0x22, 0x33 }, java.util.Arrays.copyOf(v.data, v.length));
        assertEquals(4, v.length);
    }
}