import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        String s = "A\u0001\u0080\u07FF\u0800";
        bv.putUTF8(s);

        byte[] expected = new byte[] {
                0x00, 0x0A, // byte length = 10
                0x41,       // 'A'
                0x01,       // U+0001
                (byte) 0xC2, (byte) 0x80,             // U+0080
                (byte) 0xDF, (byte) 0xBF,             // U+07FF
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80  // U+0800
        };

        assertEquals(expected.length, bv.length);
        assertArrayEquals(expected, Arrays.copyOf(bv.data, bv.length));
    }
}