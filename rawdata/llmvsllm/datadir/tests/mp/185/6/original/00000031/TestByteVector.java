import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        String s = "A\u00A2\u20AC"; // 'A' (1 byte) + '¢' (2 bytes) + '€' (3 bytes) => 6 bytes

        bv.putUTF8(s);

        byte[] expected = new byte[] {
                0x00, 0x06,                   // UTF8 byte length
                0x41,                         // 'A'
                (byte) 0xC2, (byte) 0xA2,     // '¢'
                (byte) 0xE2, (byte) 0x82, (byte) 0xAC // '€'
        };

        assertEquals(expected.length, bv.length);
        assertArrayEquals(expected, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}