import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(3);
        v.putByte(0x7F).putUTF8("A\u0800");

        byte[] expected = new byte[] {
            (byte) 0x7F,
            0x00, 0x04,          // UTF8 byte length = 4
            0x41,                // 'A'
            (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800
        };

        assertEquals(8, v.length);
        assertArrayEquals(expected, v.data);
    }
}