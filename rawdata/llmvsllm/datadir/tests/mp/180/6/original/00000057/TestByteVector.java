import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.putUTF8("A\u0000\u0800");

        assertEquals(8, v.length);

        byte[] expected = new byte[] {
            0x00, 0x06,                 // UTF8 byte length = 6
            0x41,                       // 'A'
            (byte) 0xC0, (byte) 0x80,    // U+0000 -> 2-byte UTF8 encoding
            (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800 -> 3-byte UTF8 encoding
        };
        assertArrayEquals(expected, v.data);
    }
}