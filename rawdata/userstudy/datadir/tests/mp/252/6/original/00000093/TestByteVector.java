import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);

        String s = "A\u0000\u0800B";
        bv.putUTF8(s);

        byte[] expected = new byte[] {
                0x00, 0x07,                 // UTF8 byte length = 7
                0x41,                       // 'A'
                (byte) 0xC0, (byte) 0x80,    // U+0000 -> 2-byte form in modified UTF8 style used here
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80, // U+0800 -> 3 bytes
                0x42                        // 'B'
        };

        assertArrayEquals(expected, java.util.Arrays.copyOf(bv.data, bv.length));
        assertEquals(expected.length, bv.length);
    }
}