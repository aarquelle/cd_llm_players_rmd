import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1); // force enlarge paths

        // "A" (1 byte), "\u0000" (2 bytes), "\u0800" (3 bytes), "\u007F" (1 byte)
        String s = "A\u0000\u0800\u007F";
        bv.putUTF8(s);

        byte[] expected = new byte[] {
                0x00, 0x07,                    // byte length = 7
                0x41,                          // 'A'
                (byte) 0xC0, (byte) 0x80,      // U+0000
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80, // U+0800
                0x7F                           // DEL
        };

        byte[] actual = java.util.Arrays.copyOf(bv.data, bv.length);
        assertArrayEquals(expected, actual);
    }
}