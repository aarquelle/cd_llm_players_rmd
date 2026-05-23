import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(4); // force enlarge path
        bv.putUTF8("A\u0800"); // 'A' (1 byte) + U+0800 (3 bytes) => byteLength=4

        assertEquals(6, bv.length); // 2 length bytes + 4 data bytes

        byte[] expected = new byte[] {
                0x00, 0x04,               // modified UTF-8 byte length
                0x41,                     // 'A'
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800
        };
        assertArrayEquals(expected, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}