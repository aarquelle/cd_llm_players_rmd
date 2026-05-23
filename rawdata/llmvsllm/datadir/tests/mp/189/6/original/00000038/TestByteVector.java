import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        v.putByte(0x7F)
         .putUTF8("A\u0080\u0800"); // 1 + (2 + 6) bytes = 9 total; forces non-ASCII UTF8 path & enlarge

        byte[] expected = new byte[] {
                (byte) 0x7F,
                0x00, 0x06,                   // UTF length in bytes
                0x41,                         // 'A'
                (byte) 0xC2, (byte) 0x80,      // U+0080
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800
        };

        assertArrayEquals(expected, java.util.Arrays.copyOf(v.data, v.length));
        assertEquals(expected.length, v.length);
    }
}