import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);

        String s = "A\u0001\u0080\u07FF\u0800"; // expected UTF-8 byte length = 9
        bv.putUTF8(s);

        byte[] expected = new byte[] {
                0x00, 0x09,
                0x41,
                0x01,
                (byte) 0xC2, (byte) 0x80,
                (byte) 0xDF, (byte) 0xBF,
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80
        };

        assertEquals(expected.length, bv.length);

        byte[] actual = new byte[] {
                bv.data[0], bv.data[1], bv.data[2], bv.data[3], bv.data[4], bv.data[5],
                bv.data[6], bv.data[7], bv.data[8], bv.data[9], bv.data[10]
        };
        assertArrayEquals(expected, actual);
    }
}