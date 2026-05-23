import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);

        // "A" (1 byte), '\u0080' (2 bytes), '\u0800' (3 bytes) => UTF-8 byte length = 6
        String s = "A\u0080\u0800";
        bv.putUTF8(s);

        byte[] expected = new byte[] {
            0x00, 0x06,
            0x41,
            (byte) 0xC2, (byte) 0x80,
            (byte) 0xE0, (byte) 0xA0, (byte) 0x80
        };

        assertEquals(expected.length, bv.length);
        assertArrayEquals(expected, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}