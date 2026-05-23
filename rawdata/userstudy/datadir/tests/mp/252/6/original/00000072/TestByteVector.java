import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        v.putUTF8("A\u0000\u07FF\u0800");

        byte[] out = new byte[v.length];
        out[0] = v.data[0];
        out[1] = v.data[1];
        out[2] = v.data[2];
        out[3] = v.data[3];
        out[4] = v.data[4];
        out[5] = v.data[5];
        out[6] = v.data[6];
        out[7] = v.data[7];
        out[8] = v.data[8];
        out[9] = v.data[9];

        byte[] expected = new byte[] {
            0x00, 0x08,
            0x41,
            (byte) 0xC0, (byte) 0x80,
            (byte) 0xDF, (byte) 0xBF,
            (byte) 0xE0, (byte) 0xA0, (byte) 0x80
        };

        assertEquals(10, v.length);
        assertArrayEquals(expected, out);
    }
}