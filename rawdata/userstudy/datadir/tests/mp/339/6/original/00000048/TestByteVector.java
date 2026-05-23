import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        v.putByte(0x11);
        v.putByteArray(new byte[] { 0x22, 0x33 }, 0, 2);

        assertEquals(3, v.length);
        assertArrayEquals(new byte[] { 0x11, 0x22, 0x33 }, java.util.Arrays.copyOf(v.data, v.length));
    }
}