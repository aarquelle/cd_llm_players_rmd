import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        v.putByte(0x55);

        byte[] src = new byte[] { 0x10, 0x20, 0x30, 0x40, 0x50 };
        v.putByteArray(src, 1, 3);

        assertEquals(4, v.length);
        assertArrayEquals(new byte[] { 0x55, 0x20, 0x30, 0x40 }, java.util.Arrays.copyOf(v.data, v.length));
    }
}