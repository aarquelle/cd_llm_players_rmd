import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);

        String s = "A\u0000\u0080\u07FF\u0800"; // 1 + 2 + 2 + 2 + 3 = 10 bytes
        bv.putUTF8(s);

        byte[] expected = new byte[] {
            0x00, 0x0A,
            0x41,
            (byte) 0xC0, (byte) 0x80,
            (byte) 0xC2, (byte) 0x80,
            (byte) 0xDF, (byte) 0xBF,
            (byte) 0xE0, (byte) 0xA0, (byte) 0x80
        };

        assertEquals(12, bv.length);
        assertArrayEquals(expected, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}