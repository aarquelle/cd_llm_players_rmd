import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);
        bv.putUTF8("A\u0080\u0800");

        byte[] actual = Arrays.copyOf(bv.data, bv.length);
        byte[] expected = new byte[] {
                0x00, 0x06,                   // byte length = 6
                0x41,                         // 'A'
                (byte) 0xC2, (byte) 0x80,     // U+0080
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800
        };

        assertEquals(8, bv.length);
        assertArrayEquals(expected, actual);
    }
}