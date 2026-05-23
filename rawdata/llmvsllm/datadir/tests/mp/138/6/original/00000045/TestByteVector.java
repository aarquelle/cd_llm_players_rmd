import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putByte(0x11);

        byte[] src = new byte[] { (byte) 0x7E, (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04 };
        bv.putByteArray(src, 1, 3); // copy 0x01,0x02,0x03

        assertArrayEquals(new byte[] { (byte) 0x11, (byte) 0x01, (byte) 0x02, (byte) 0x03 },
                java.util.Arrays.copyOf(bv.data, bv.length));
        assertEquals(4, bv.length);
    }
}