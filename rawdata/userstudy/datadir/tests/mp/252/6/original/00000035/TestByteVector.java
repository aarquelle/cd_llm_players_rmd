import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(4);
        v.putByte(0x7F);
        v.putUTF8("A\u0000\u07FF\u0800");

        byte[] expected = new byte[] {
            (byte) 0x7F,
            0x00, 0x08,                   // actual byte length per implementation
            0x41,                         // 'A'
            (byte) 0xC0, (byte) 0x80,     // U+0000 (overlong encoding in this implementation)
            (byte) 0xDF, (byte) 0xBF,     // U+07FF
            (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800
        };

        assertEquals(expected.length, v.length);
        assertArrayEquals(expected, Arrays.copyOf(v.data, v.length));
    }
}