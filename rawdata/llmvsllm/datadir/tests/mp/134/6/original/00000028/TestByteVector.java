import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                String s = "A\u0000\u0080\u07FF\u0800Z";
        ByteVector bv = new ByteVector(2);
        bv.putUTF8(s);

        byte[] expected = new byte[] {
                0x00, 0x0B,                               // byte length = 11
                (byte) 'A',
                (byte) 0xC0, (byte) 0x80,                 // U+0000
                (byte) 0xC2, (byte) 0x80,                 // U+0080
                (byte) 0xDF, (byte) 0xBF,                 // U+07FF
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80,    // U+0800
                (byte) 'Z'
        };

        assertEquals(expected.length, bv.length);
        assertArrayEquals(expected, bv.data);
    }
}