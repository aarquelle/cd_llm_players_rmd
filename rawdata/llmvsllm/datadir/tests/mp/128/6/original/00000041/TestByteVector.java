import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        v.putByte(0x55);

        byte[] src = new byte[] { 9, (byte) 0xAB, (byte) 0xCD, 7 };
        v.putByteArray(src, 1, 2);

        assertArrayEquals(new byte[] { 0x55, (byte) 0xAB, (byte) 0xCD }, java.util.Arrays.copyOf(v.data, v.length));
        assertEquals(3, v.length);
    }
}