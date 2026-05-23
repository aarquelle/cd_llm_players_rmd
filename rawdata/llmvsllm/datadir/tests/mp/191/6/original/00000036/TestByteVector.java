import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(10);
        bv.putByte(0x7F);
        bv.putByteArray(null, 0, 3);

        assertEquals(4, bv.length);
        assertArrayEquals(new byte[] { 0x7F, 0x00, 0x00, 0x00 },
                java.util.Arrays.copyOf(bv.data, bv.length));
    }
}