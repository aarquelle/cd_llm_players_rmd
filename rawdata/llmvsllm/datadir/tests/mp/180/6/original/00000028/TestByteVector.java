import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(4);
        v.putByte(0x7F);
        v.putUTF8("A\u00A2B"); // UTF8 bytes: 0x41, 0xC2 0xA2, 0x42 => 4 bytes

        assertArrayEquals(new byte[] {
                0x7F,
                0x00, 0x04,
                0x41,
                (byte) 0xC2, (byte) 0xA2,
                0x42
        }, java.util.Arrays.copyOf(v.data, v.length));
        assertEquals(7, v.length);
    }
}