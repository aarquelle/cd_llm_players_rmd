import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.putUTF8("A\u0800B"); // 'A' (1 byte) + U+0800 (3 bytes) + 'B' (1 byte) => 5 bytes

        assertArrayEquals(new byte[] {
                0x00, 0x05,                 // UTF8 byte length = 5
                0x41,                       // 'A'
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80, // U+0800
                0x42                        // 'B'
        }, java.util.Arrays.copyOf(v.data, v.length));
        assertEquals(7, v.length); // 2 (len header) + 5 (bytes)
    }
}