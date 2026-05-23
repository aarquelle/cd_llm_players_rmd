import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(4);
        bv.putByte(0x7F);
        bv.putInt(0x01020304);

        assertEquals(5, bv.length);
        assertArrayEquals(new byte[] { (byte) 0x7F, 0x01, 0x02, 0x03, 0x04 }, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}