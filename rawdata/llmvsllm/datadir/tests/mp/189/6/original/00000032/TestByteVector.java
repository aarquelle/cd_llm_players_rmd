import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putByte(0x11);

        byte[] src = new byte[] { 0x01, 0x02, 0x03, 0x04 };
        bv.putByteArray(src, 1, 2);          // expect 0x02, 0x03
        bv.putByteArray(null, 0, 2);         // two zero bytes

        assertEquals(5, bv.length);
        assertArrayEquals(new byte[] { 0x11, 0x02, 0x03, 0x00, 0x00 }, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}