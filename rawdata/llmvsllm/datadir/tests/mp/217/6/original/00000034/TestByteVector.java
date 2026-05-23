import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putShort(0xABCD).putShort(0x1234);

        assertEquals(4, bv.length);
        assertArrayEquals(new byte[] { (byte) 0xAB, (byte) 0xCD, 0x12, 0x34 }, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}