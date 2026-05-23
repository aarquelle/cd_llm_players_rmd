import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.putByte(0x11);
        byte[] src = new byte[] { 0x55, 0x66, 0x77, (byte) 0x88 };

        v.putByteArray(src, 1, 2); // copy {0x66, 0x77}

        assertArrayEquals(new byte[] { 0x11, 0x66, 0x77 }, Arrays.copyOf(v.data, v.length));
        assertEquals(3, v.length);
    }
}