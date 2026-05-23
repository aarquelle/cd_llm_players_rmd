import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        v.putByte(0x12);
        v.putInt(0x3456789A);

        assertArrayEquals(new byte[] { 0x12, 0x34, 0x56, 0x78, (byte) 0x9A },
                java.util.Arrays.copyOf(v.data, v.length));
        assertTrue(v.data.length >= v.length);
    }
}