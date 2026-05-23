import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                String s = "A\u0000\u007F\u0080\u07FF\u0800Z";

        // Expected bytes for modified UTF-8 (DataOutputStream.writeUTF format):
        // length prefix (2 bytes) + encoded bytes
        byte[] expected = new byte[] {
                0x00, 0x0C,             // 12 bytes follow
                0x41,                   // 'A'
                (byte) 0xC0, (byte) 0x80,// U+0000
                0x7F,                   // U+007F
                (byte) 0xC2, (byte) 0x80,// U+0080
                (byte) 0xDF, (byte) 0xBF,// U+07FF
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80, // U+0800
                0x5A                    // 'Z'
        };

        ByteVector bv = new ByteVector(1);
        bv.putUTF8(s);

        assertArrayEquals(expected, java.util.Arrays.copyOf(bv.data, bv.length));
        assertEquals(expected.length, bv.length);
    }
}