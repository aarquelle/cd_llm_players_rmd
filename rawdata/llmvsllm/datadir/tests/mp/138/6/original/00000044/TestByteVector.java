import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.putByte(0x7F);

        byte[] src = new byte[] { 10, 20, 30, 40 };
        v.putByteArray(src, 1, 2);          // copy 20,30
        v.putByteArray(null, 0, 3);         // append 3 zeros

        assertArrayEquals(new byte[] { 0x7F, 20, 30, 0, 0, 0 }, java.util.Arrays.copyOf(v.data, v.length));
        assertEquals(6, v.length);
    }
}