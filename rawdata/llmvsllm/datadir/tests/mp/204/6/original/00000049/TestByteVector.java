import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);
        bv.length = 1;
        bv.data[0] = (byte) 0x7E;

        bv.putInt(0x12345678);

        assertArrayEquals(new byte[] { (byte) 0x7E, 0x12, 0x34, 0x56, 0x78 }, java.util.Arrays.copyOf(bv.data, bv.length));
        assertEquals(5, bv.length);
    }
}