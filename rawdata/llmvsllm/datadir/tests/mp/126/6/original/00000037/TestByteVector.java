import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                String s = "A\u0001\u007F\u0080\u07FF\u0800\u20AC\u0000Z";
        ByteVector v = new ByteVector(3).putUTF8(s);

        byte[] expected = new byte[] {
                0x00, 0x11, // byte length = 17
                0x41,       // 'A'
                0x01,       // \u0001
                0x7F,       // \u007F
                (byte) 0xC2, (byte) 0x80,             // \u0080
                (byte) 0xDF, (byte) 0xBF,             // \u07FF
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80, // \u0800
                (byte) 0xE2, (byte) 0x82, (byte) 0xAC, // \u20AC
                (byte) 0xC0, (byte) 0x80,             // \u0000 (modified UTF-8)
                0x5A        // 'Z'
        };

        assertEquals(expected.length, v.length);
        assertEquals(new String(expected), new String(java.util.Arrays.copyOf(v.data, v.length)));
    }
}