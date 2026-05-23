import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        String s = "\u0000\u0080\u0800"; // 2-byte, 2-byte, 3-byte in modified UTF-8

        v.putUTF8(s);

        assertEquals(2 + 7, v.length);
        assertArrayEquals(
                new byte[] {
                        0, 7,                  // byte length = 7
                        (byte) 0xC0, (byte) 0x80, // U+0000
                        (byte) 0xC2, (byte) 0x80, // U+0080
                        (byte) 0xE0, (byte) 0xA0, (byte) 0x80  // U+0800
                },
                java.util.Arrays.copyOf(v.data, v.length)
        );
    }
}