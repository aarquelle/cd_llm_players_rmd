import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        v.putByte(0x11);

        byte[] src = new byte[] { (byte) 0x22, (byte) 0x33, (byte) 0x44 };
        v.putByteArray(src, 1, 2);

        assertEquals(3, v.length);
        assertArrayEquals(new byte[] { (byte) 0x11, (byte) 0x33, (byte) 0x44 }, java.util.Arrays.copyOf(v.data, v.length));
    }
}