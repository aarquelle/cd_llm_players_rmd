import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);
        bv.putUTF8("A\u0800B"); // 'A' (1 byte) + U+0800 (3 bytes) + 'B' (1 byte) => 5 bytes, charLength=3

        assertEquals(7, bv.length); // 2-byte length prefix + 5 bytes payload

        byte[] expected = new byte[] {
                0, 5,                 // UTF8 byte length
                0x41,                 // 'A'
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80, // U+0800
                0x42                  // 'B'
        };
        assertArrayEquals(expected, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}