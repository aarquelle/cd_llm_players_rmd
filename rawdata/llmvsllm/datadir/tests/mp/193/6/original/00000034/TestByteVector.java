import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3);
        bv.putByte(0x7F);

        bv.putUTF8("A\u0080\u0800");
        // expected bytes:
        // 7F
        // 00 06 (utf8 byte length: 'A' 1 + U+0080 2 + U+0800 3 = 6)
        // 41
        // C2 80
        // E0 A0 80
        byte[] expected = new byte[] {
                (byte) 0x7F,
                0x00, 0x06,
                0x41,
                (byte) 0xC2, (byte) 0x80,
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80
        };

        assertArrayEquals(expected, java.util.Arrays.copyOf(bv.data, bv.length));
        assertEquals(expected.length, bv.length);
    }
}