import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);

        bv.putByte(0x7F)
          .putUTF8("A\u00A2\u0800"); // A (1), ¢ (2), U+0800 (3) => byteLength=6, charLength=3

        bv.putByteArray(null, 0, 2);

        byte[] actual = bv.data;
        int n = bv.length;
        assertEquals(1 + 2 + 6 + 2, n);

        byte[] expected = new byte[] {
                (byte)0x7F,
                0x00, 0x06,
                0x41,
                (byte)0xC2, (byte)0xA2,
                (byte)0xE0, (byte)0xA0, (byte)0x80,
                0x00, 0x00
        };
        assertArrayEquals(expected, java.util.Arrays.copyOf(actual, n));
    }
}