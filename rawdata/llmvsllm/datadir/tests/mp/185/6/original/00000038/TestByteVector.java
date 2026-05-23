import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(4);
        v.putByte(0x55).putUTF8("A\u0080\u0800");

        byte[] expected = new byte[] {
                0x55,
                0x00, 0x06, // UTF length in bytes: 1 + 2 + 3 = 6
                0x41,       // 'A'
                (byte) 0xC2, (byte) 0x80,             // U+0080
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80  // U+0800
        };

        assertEquals(expected.length, v.length);
        assertArrayEquals(expected, java.util.Arrays.copyOf(v.data, v.length));
    }
}