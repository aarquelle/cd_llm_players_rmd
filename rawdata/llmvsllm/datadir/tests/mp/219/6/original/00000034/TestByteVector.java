import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);
        bv.putByte(0x7F).putUTF8("A\u00E9\u0800"); // A (1), é (2), U+0800 (3)

        byte[] expected = new byte[] {
                (byte) 0x7F,
                0x00, 0x06,
                0x41,
                (byte) 0xC3, (byte) 0xA9,
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80
        };

        assertEquals(10, bv.length);
        assertArrayEquals(expected, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}