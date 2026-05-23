import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(3); // force enlarge path
        v.putUTF8("\u0000\u07FF\u0800"); // 2 + (2 + 2 + 3) = 9 total bytes

        assertEquals(9, v.length);
        assertArrayEquals(new byte[] {
                0x00, 0x07,                   // UTF8 byte length = 7
                (byte) 0xC0, (byte) 0x80,      // U+0000
                (byte) 0xDF, (byte) 0xBF,      // U+07FF
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800
        }, java.util.Arrays.copyOf(v.data, v.length));
    }
}