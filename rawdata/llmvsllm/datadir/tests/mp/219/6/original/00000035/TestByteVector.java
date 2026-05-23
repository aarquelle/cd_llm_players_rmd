import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);
        bv.putByte(0x7F).putUTF8("A\u00E9\u0800"); // byteLength = 1 + 2 + 3 = 6, but buggy impl writes 5

        byte[] expected = new byte[] {
                (byte) 0x7F,
                0x00, 0x05,       // current implementation's (incorrect) length prefix
                0x41,             // 'A'
                (byte) 0xC3, (byte) 0xA9, // 'é'
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800
        };

        assertEquals(9, bv.length);
        assertArrayEquals(expected, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}