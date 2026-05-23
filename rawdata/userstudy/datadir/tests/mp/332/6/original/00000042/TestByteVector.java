import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        v.putByte(0x12).putByte(0x34); // triggers enlarge(1) when adding second byte
        assertArrayEquals(new byte[] { 0x12, 0x34 }, java.util.Arrays.copyOf(v.data, v.length));
        assertEquals(2, v.data.length);
    }
}