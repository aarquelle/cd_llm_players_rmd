import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.putByte(0x11);

        byte[] src = new byte[] { 0x55, 0x22, 0x33, 0x44, 0x66 };
        v.putByteArray(src, 1, 3);     // copies 0x22,0x33,0x44; forces enlarge from capacity 2
        v.putByteArray(null, 0, 2);    // appends two zero bytes

        assertEquals(6, v.length);
        assertArrayEquals(new byte[] { 0x11, 0x22, 0x33, 0x44, 0x00, 0x00 }, java.util.Arrays.copyOf(v.data, v.length));
    }
}