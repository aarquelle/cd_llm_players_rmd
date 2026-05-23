import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putUTF8("\u0000\u0800");

        byte[] expected = new byte[] {
            0x00, 0x05,             // UTF length: 5 bytes
            (byte) 0xC0, (byte) 0x80,// U+0000 encoded as 2-byte modified UTF-8
            (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800 encoded as 3-byte UTF-8
        };

        assertEquals(7, bv.length);
        assertArrayEquals(expected, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}