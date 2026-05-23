import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        v.putUTF8("\u0080"); // U+0080 should encode as 2 bytes: C2 80, with length prefix 0x0002
        v.putByteArray(null, 0, 3); // append 3 zero bytes

        assertArrayEquals(new byte[] {0, 2, (byte) 0xC2, (byte) 0x80, 0, 0, 0}, java.util.Arrays.copyOf(v.data, v.length));
        assertEquals(7, v.length);
    }
}