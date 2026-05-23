import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);
        bv.putUTF8("A\u00E9\u0800"); // 'A' (1 byte), 'é' (2 bytes), U+0800 (3 bytes) => 6 bytes payload

        assertEquals(8, bv.length);

        byte[] expected = new byte[] {
                0x00, 0x06,                 // modified UTF-8 length
                0x41,                       // 'A'
                (byte) 0xC3, (byte) 0xA9,   // 'é'
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800
        };
        assertArrayEquals(expected, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}