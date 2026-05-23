import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        v.putByte(0x55);

        byte[] src = new byte[] { 10, 11, 12, 13, 14 };
        v.putByteArray(src, 2, 3);

        assertArrayEquals(new byte[] { (byte) 0x55, 12, 13, 14 }, java.util.Arrays.copyOf(v.data, v.length));
        assertEquals(4, v.length);
    }
}