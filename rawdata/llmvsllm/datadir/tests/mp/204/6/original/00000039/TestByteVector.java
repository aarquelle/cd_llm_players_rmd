import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(4);
        bv.putByte(0x55);

        String s = "\u00A2\u0800"; // U+00A2 -> 2 bytes, U+0800 -> 3 bytes, total 5
        bv.putUTF8(s);

        assertEquals(1 + 2 + 5, bv.length);
        assertArrayEquals(new byte[] { 0x55, 0x00, 0x05, (byte) 0xC2, (byte) 0xA2, (byte) 0xE0, (byte) 0xA0, (byte) 0x80 },
                java.util.Arrays.copyOf(bv.data, bv.length));
    }
}