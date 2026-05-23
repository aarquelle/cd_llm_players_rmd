import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(4);
        String s = "A\u0000\u0080\u0800Z";
        v.putUTF8(s);

        byte[] expected = new byte[] {
                0x00, 0x0A,                   // byte length = 10
                0x41,                         // 'A'
                (byte) 0xC0, (byte) 0x80,      // U+0000
                (byte) 0xC2, (byte) 0x80,      // U+0080
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80, // U+0800
                0x5A                          // 'Z'
        };

        assertEquals(expected.length, v.length);
        assertArrayEquals(expected, java.util.Arrays.copyOf(v.data, v.length));
    }
}