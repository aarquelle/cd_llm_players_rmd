import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(3);
        v.putByte(0x7E);
        v.putInt(0x12345678);

        assertEquals(5, v.length);
        assertArrayEquals(new byte[] { (byte) 0x7E, 0x12, 0x34, 0x56, 0x78 },
                java.util.Arrays.copyOf(v.data, v.length));
    }
}