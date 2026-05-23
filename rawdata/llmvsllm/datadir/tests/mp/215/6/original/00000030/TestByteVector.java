import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                String s = "A\u0001\u007F\u0080\u07FF\u0800\u20ACZ";

        ByteVector bv = new ByteVector(2);
        bv.putUTF8(s);

        byte[] expected = new byte[] {
                0x00, 0x0D,
                0x41,
                0x01,
                0x7F,
                (byte) 0xC2, (byte) 0x80,
                (byte) 0xDF, (byte) 0xBF,
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80,
                (byte) 0xE2, (byte) 0x82, (byte) 0xAC,
                0x5A
        };

        assertEquals(expected.length, bv.length);
        assertArrayEquals(expected, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}