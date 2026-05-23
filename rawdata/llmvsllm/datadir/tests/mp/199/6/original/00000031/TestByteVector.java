import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);
        bv.putUTF8("A\u0000\u00A2\u0800"); // 'A', NUL, '¢', U+0800

        byte[] expected = new byte[] {
                0x00, 0x08,             // UTF-8 byte length = 1 + 2 + 2 + 3 = 8
                0x41,                   // 'A'
                (byte) 0xC0, (byte) 0x80, // NUL encoded as 2 bytes in modified UTF-8
                (byte) 0xC2, (byte) 0xA2, // '¢'
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800
        };

        assertArrayEquals(expected, Arrays.copyOf(bv.data, bv.length));
        assertEquals(expected.length, bv.length);
    }
}