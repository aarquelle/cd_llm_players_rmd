import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.putUTF8("A\u20AC\u0800"); // 'A' (1), '€' (3), U+0800 (3) => 7 bytes

        assertEquals(9, v.length); // 2-byte length header + 7 payload bytes
        assertArrayEquals(new byte[] {
                0x00, 0x07, // UTF8 byte length = 7
                0x41,       // 'A'
                (byte)0xE2, (byte)0x82, (byte)0xAC, // '€'
                (byte)0xE0, (byte)0xA0, (byte)0x80  // U+0800
        }, java.util.Arrays.copyOf(v.data, v.length));
    }
}