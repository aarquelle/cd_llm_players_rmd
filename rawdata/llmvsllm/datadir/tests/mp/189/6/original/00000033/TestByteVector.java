import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putByte(0x11);
        bv.putByteArray(new byte[] { 0x00, 0x22, 0x33, 0x44 }, 1, 2);

        assertArrayEquals(new byte[] { 0x11, 0x22, 0x33 }, java.util.Arrays.copyOf(bv.data, bv.length));
        assertEquals(3, bv.length);
    }
}