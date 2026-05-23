import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(4); // force enlarge path
        String s = "A\u0000\u07FF\u0800"; // 1-byte, 2-byte (NUL), 2-byte max, 3-byte min
        bv.putUTF8(s);

        byte[] expected = new byte[] {
                0x00, 0x08, // byte length = 1 + 2 + 2 + 3 = 8
                0x41,       // 'A'
                (byte) 0xC0, (byte) 0x80,                 // U+0000
                (byte) 0xDF, (byte) 0xBF,                 // U+07FF
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80      // U+0800
        };

        assertArrayEquals(expected, java.util.Arrays.copyOf(bv.data, bv.length));
        assertEquals(expected.length, bv.length);
    }
}