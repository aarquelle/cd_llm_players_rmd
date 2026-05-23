import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                String s = "A\u0000\u0800"; // 1-byte, 2-byte (NUL), 3-byte
        ByteVector bv = new ByteVector(2); // force enlarge paths

        bv.putUTF8(s);

        byte[] expected = new byte[] {
                0x00, 0x06,                 // byte length = 6
                0x41,                       // 'A'
                (byte) 0xC0, (byte) 0x80,   // NUL in modified UTF-8
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800
        };

        assertEquals(expected.length, bv.length);
        assertArrayEquals(expected, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}