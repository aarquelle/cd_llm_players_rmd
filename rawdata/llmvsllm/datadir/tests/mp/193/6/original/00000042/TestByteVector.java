import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        String s = "A\u0800\u00A2"; // 'A' (1 byte), U+0800 (3 bytes), U+00A2 (2 bytes) => 6 bytes
        v.putUTF8(s);

        assertEquals(8, v.length); // 2-byte length prefix + 6 bytes payload
        assertArrayEquals(new byte[] {
                0x00, 0x06,                 // UTF length = 6
                0x41,                       // 'A'
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80, // U+0800
                (byte) 0xC2, (byte) 0xA2    // U+00A2
        }, java.util.Arrays.copyOf(v.data, v.length));
    }
}