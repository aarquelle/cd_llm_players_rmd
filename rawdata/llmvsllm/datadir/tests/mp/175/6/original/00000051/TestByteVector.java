import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                String s = "\u0000A\u007F\u0080\u07FF\u0800\u1234"; // forces 1,2,3-byte paths and null encoding

        // Expected bytes per DataOutputStream.writeUTF (modified UTF-8):
        // total utf byte length = 1(null) + 1('A') + 2(0x007F) + 2(0x0080) + 2(0x07FF) + 3(0x0800) + 3(0x1234) = 14
        byte[] expected = new byte[] {
                0x00, 0x0E,                         // length (14)
                (byte) 0xC0, (byte) 0x80,           // U+0000 -> 0xC0 0x80
                0x41,                               // 'A'
                (byte) 0xC1, (byte) 0xBF,           // U+007F (overlong in modified UTF-8)
                (byte) 0xC2, (byte) 0x80,           // U+0080
                (byte) 0xDF, (byte) 0xBF,           // U+07FF
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80, // U+0800
                (byte) 0xE1, (byte) 0x88, (byte) 0xB4  // U+1234
        };

        ByteVector bv = new ByteVector(1);
        bv.putUTF8(s);

        assertEquals(expected.length, bv.length);
        assertArrayEquals(expected, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}