import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        String s = "A\u07FF\u0800B";

        bv.putUTF8(s);

        byte[] expected = new byte[] {
            0x00, 0x09,
            0x41,
            (byte) 0xDF, (byte) 0xBF,
            (byte) 0xE0, (byte) 0xA0, (byte) 0x80,
            0x42
        };

        assertEquals(expected.length, bv.length);
        assertArrayEquals(expected, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}