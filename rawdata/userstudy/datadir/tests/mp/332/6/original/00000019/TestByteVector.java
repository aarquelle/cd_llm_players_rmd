import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putByte(0x01);

        byte[] src = new byte[] { 0x11, 0x22, 0x33, 0x44 };
        bv.putByteArray(src, 1, 3);

        assertArrayEquals(new byte[] { 0x01, 0x22, 0x33, 0x44 }, java.util.Arrays.copyOf(bv.data, bv.length));
        assertTrue(bv.data.length >= 4);
    }
}