import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1); // force enlarge path
        bv.putByte(0x7E);
        bv.putShort(0x1234);

        assertArrayEquals(new byte[] { (byte) 0x7E, (byte) 0x12, (byte) 0x34 }, java.util.Arrays.copyOf(bv.data, bv.length));
        assertEquals(3, bv.length);
    }
}