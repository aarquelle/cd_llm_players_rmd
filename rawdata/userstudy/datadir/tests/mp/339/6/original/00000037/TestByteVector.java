import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(64);
        String s = "A\u0080\u0800"; // 1-byte, 2-byte, 3-byte
        bv.putUTF8(s);

        byte[] expected = new byte[] {
            0x00, 0x06,               // UTF-8 byte length = 1 + 2 + 3 = 6
            0x41,                     // 'A'
            (byte) 0xC2, (byte) 0x80,  // U+0080
            (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800
        };

        assertArrayEquals(expected, Arrays.copyOf(bv.data, bv.length));
    }
}