import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(4);
        bv.putByte(0x11);

        byte[] src = new byte[] { 0x01, 0x02, 0x03, 0x04, 0x05 };
        bv.putByteArray(src, 1, 3); // copies 0x02,0x03,0x04 at index 1..3

        assertArrayEquals(new byte[] { 0x11, 0x02, 0x03, 0x04 }, java.util.Arrays.copyOf(bv.data, bv.length));
        assertEquals(4, bv.data.length);
    }
}