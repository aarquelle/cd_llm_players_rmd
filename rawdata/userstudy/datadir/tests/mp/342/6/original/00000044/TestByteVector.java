import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.putByte(0x7A);
        byte[] src = new byte[] { 9, 0x10, 0x20, 0x30, 0x40 };
        v.putByteArray(src, 1, 3);

        assertArrayEquals(new byte[] { 0x7A, 0x10, 0x20, 0x30 }, java.util.Arrays.copyOf(v.data, v.length));
        assertEquals(4, v.length);
    }
}