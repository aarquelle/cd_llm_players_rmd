import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(4);
        v.putByte(0x7F);
        v.putUTF8("A\u00A2B"); // 'A' (1) + '¢' (2) + 'B' (1) => 4 UTF8 bytes, 3 chars

        assertArrayEquals(new byte[] {
                0x7F,
                0x00, 0x04,
                0x41,
                (byte) 0xC2, (byte) 0xA2,
                0x42
        }, java.util.Arrays.copyOf(v.data, v.length));
        assertEquals(8, v.length);
    }
}