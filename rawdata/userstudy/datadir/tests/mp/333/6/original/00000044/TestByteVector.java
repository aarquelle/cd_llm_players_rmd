import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);
        bv.putByte(0x7F);

        byte[] src = new byte[] { 10, 20, 30, 40, 50 };
        bv.putByteArray(src, 1, 3);

        assertEquals(4, bv.length);
        assertArrayEquals(new byte[] { 0x7F, 20, 30, 40 }, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}