import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.putUTF8("\u0080A");

        byte[] expected = new byte[] {
                0x00, 0x03,              // UTF length = 3 bytes
                (byte) 0xC2, (byte) 0x80, // U+0080 in UTF-8
                0x41                     // 'A'
        };

        assertEquals(expected.length, v.length);
        assertArrayEquals(expected, java.util.Arrays.copyOf(v.data, v.length));
    }
}