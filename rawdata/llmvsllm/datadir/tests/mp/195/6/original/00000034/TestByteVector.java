import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(3); // force enlarge path during putUTF8
        v.putUTF8("\u0000\u0800"); // NUL -> 2 bytes, U+0800 -> 3 bytes, total 5

        assertEquals(7, v.length); // 2 (length prefix) + 5 (encoded bytes)

        byte[] expected = new byte[] {
            0x00, 0x05,             // UTF length = 5
            (byte) 0xC0, (byte) 0x80, // U+0000 in modified UTF-8
            (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800
        };
        assertArrayEquals(expected, new byte[] { v.data[0], v.data[1], v.data[2], v.data[3], v.data[4], v.data[5], v.data[6] });
    }
}