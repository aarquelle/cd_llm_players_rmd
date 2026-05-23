import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(4);
        bv.putByte(0x7F);
        bv.putUTF8("\u0080\u0800");

        assertEquals(1 + 2 + 5, bv.length);

        byte[] expected = new byte[] {
            (byte) 0x7F,
            0x00, 0x05,
            (byte) 0xC2, (byte) 0x80,
            (byte) 0xE0, (byte) 0xA0, (byte) 0x80
        };
        assertArrayEquals(expected, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}