import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(4);
        bv.putUTF8("A\u07FF\u0800"); // 1 + 2 + 3 = 6 bytes, forces general path and enlarge

        int expectedLength = 2 + 6;
        byte[] expected = new byte[] {
                0x00, 0x06,             // byte length
                0x41,                   // 'A'
                (byte) 0xDF, (byte) 0xBF, // U+07FF
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800
        };

        assertEquals(expectedLength, bv.length);
        assertArrayEquals(expected, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}