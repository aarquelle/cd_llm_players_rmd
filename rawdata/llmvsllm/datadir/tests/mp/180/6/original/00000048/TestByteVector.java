import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.putUTF8("A\u0080\u0800");

        byte[] expected = new byte[] {
                0x00, 0x06, // modified UTF8 length in bytes
                0x41,       // 'A'
                (byte) 0xC2, (byte) 0x80, // U+0080 -> 2 bytes
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800 -> 3 bytes
        };

        assertEquals(8, v.length);
        assertArrayEquals(expected, java.util.Arrays.copyOf(v.data, v.length));
    }
}