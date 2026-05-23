import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);

        String s = "A\u0000\u07FF\u0800"; // 'A'(1) + NUL(2) + U+07FF(2) + U+0800(3) => 8 bytes payload
        bv.putUTF8(s);

        assertEquals(10, bv.length);

        assertArrayEquals(new byte[] {
                0x00, 0x08,                         // modified UTF-8 byte length
                0x41,                               // 'A'
                (byte) 0xC0, (byte) 0x80,           // NUL
                (byte) 0xDF, (byte) 0xBF,           // U+07FF
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800
        }, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}