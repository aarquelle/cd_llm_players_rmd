import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3);
        bv.putByte(0x55);
        bv.putInt(0x01020380);

        assertArrayEquals(new byte[] { 0x55, 0x01, 0x02, 0x03, (byte) 0x80 },
                java.util.Arrays.copyOf(bv.data, bv.length));
        assertEquals(5, bv.length);
    }
}