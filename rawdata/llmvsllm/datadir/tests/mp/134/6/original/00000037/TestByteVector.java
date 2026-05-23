import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putUTF8("A\u0800B"); // requires 1 + 3 + 1 bytes, total byteLength=5, plus 2 length bytes => 7 (forces enlarge)
        bv.putByteArray(null, 0, 2); // append two zero bytes

        byte[] expected = new byte[] {
            0x00, 0x05, // UTF length
            0x41,       // 'A'
            (byte)0xE0, (byte)0xA0, (byte)0x80, // U+0800
            0x42,       // 'B'
            0x00, 0x00  // null byte array padding
        };

        assertEquals(expected.length, bv.length);
        assertArrayEquals(expected, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}