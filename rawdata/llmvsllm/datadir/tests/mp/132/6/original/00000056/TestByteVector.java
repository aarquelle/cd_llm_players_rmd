import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        v.putByte(0x55);

        byte[] src = new byte[] { (byte) 0x10, (byte) 0x20, (byte) 0x30, (byte) 0x40 };
        v.putByteArray(src, 1, 2); // should copy 0x20, 0x30

        assertArrayEquals(new byte[] { (byte) 0x55, (byte) 0x20, (byte) 0x30 }, java.util.Arrays.copyOf(v.data, v.length));
        assertEquals(3, v.length);
    }
}