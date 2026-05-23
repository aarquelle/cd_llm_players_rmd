import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);
        bv.putByte(0x11).putByte(0x22);

        assertTrue(bv.data.length >= 2);
        assertArrayEquals(new byte[] { 0x11, 0x22 }, Arrays.copyOf(bv.data, bv.length));
    }
}