import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1); // force enlargement paths
        String s = "A\u0080\u0800"; // 1-byte + 2-byte + 3-byte UTF8 => 6 bytes

        bv.putUTF8(s);

        byte[] expected = new byte[] {
                0x00, 0x06,             // UTF8 byte length
                0x41,                   // 'A'
                (byte) 0xC2, (byte) 0x80, // U+0080
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800
        };

        assertEquals(expected.length, bv.length);
        assertArrayEquals(expected, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}