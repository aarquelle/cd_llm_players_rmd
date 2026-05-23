import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3);
        bv.putUTF8("\u0080\u0800"); // U+0080 -> 2 bytes, U+0800 -> 3 bytes, total 5

        assertEquals(7, bv.length); // 2 (length prefix) + 5 (encoded bytes)

        byte[] expected = new byte[] {
                0x00, 0x05,                 // UTF8 byte length
                (byte) 0xC2, (byte) 0x80,    // U+0080
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800
        };
        assertArrayEquals(expected, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}