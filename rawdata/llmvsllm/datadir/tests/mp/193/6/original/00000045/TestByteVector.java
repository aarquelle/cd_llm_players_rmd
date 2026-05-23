import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(4);
        v.putByte(0x7F);
        v.putUTF8("\u00A2\u0905"); // 2 bytes + 3 bytes => 5 bytes

        assertArrayEquals(new byte[] {
                0x7F,
                0x00, 0x05,
                (byte) 0xC2, (byte) 0xA2,
                (byte) 0xE0, (byte) 0xA4, (byte) 0x85
        }, Arrays.copyOf(v.data, v.length));
        assertEquals(8, v.length);
    }
}