import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(4);
        v.putByte(0x7F);
        v.putUTF8("\u00A2\u0905"); // U+00A2 (2 bytes) + U+0905 (3 bytes) => 5 bytes

        byte[] d = v.data;
        assertArrayEquals(new byte[] {
                0x7F,
                0x00, 0x05,
                (byte) 0xC2, (byte) 0xA2,
                (byte) 0xE0, (byte) 0xA4, (byte) 0x85
        }, java.util.Arrays.copyOf(d, v.length));
        assertEquals(9, v.length);
    }
}