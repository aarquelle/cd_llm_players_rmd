import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3);
        bv.putByte(0x7E);
        bv.put12(0xA5, 0x1234);

        assertEquals(4, bv.length);
        assertArrayEquals(new byte[] { (byte) 0x7E, (byte) 0xA5, 0x12, 0x34 }, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}