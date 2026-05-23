import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3); // force enlarge path
        bv.putByte(0x7F);
        bv.putUTF8("A\u0800"); // 'A' (1 byte) + U+0800 (3 bytes) => 4 UTF8 bytes, header=0x0004

        byte[] expectedPrefix = new byte[] {
                0x7F,
                0x00, 0x04, // UTF8 byte length
                0x41,       // 'A'
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800
        };

        assertEquals(1 + 2 + 4, bv.length);
        assertArrayEquals(expectedPrefix, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}