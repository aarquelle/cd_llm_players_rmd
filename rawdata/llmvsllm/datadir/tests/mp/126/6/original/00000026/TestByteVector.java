import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1); // force enlarge paths
        String s = "A\u0000\u07FF\u0800"; // 1-byte + 2-byte + 2-byte + 3-byte => 8 bytes

        bv.putUTF8(s);

        assertEquals(10, bv.length); // 2 (length prefix) + 8 (utf8 bytes)

        byte[] expected = new byte[] {
                0x00, 0x08,
                0x41,
                (byte) 0xC0, (byte) 0x80,
                (byte) 0xDF, (byte) 0xBF,
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80
        };
        byte[] actual = java.util.Arrays.copyOf(bv.data, bv.length);
        assertArrayEquals(expected, actual);
    }
}