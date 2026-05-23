import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3);
        String s = "A\u00A2\u0905"; // UTF-8 byte length = 6
        bv.putUTF8(s);

        byte[] utf8 = s.getBytes("UTF-8");
        byte[] expected = new byte[2 + utf8.length];
        expected[0] = (byte) ((utf8.length >>> 8) & 0xFF);
        expected[1] = (byte) (utf8.length & 0xFF);
        expected[2] = utf8[0];
        expected[3] = utf8[1];
        expected[4] = utf8[2];
        expected[5] = utf8[3];
        expected[6] = utf8[4];
        expected[7] = utf8[5];

        assertEquals(8, bv.length);
        assertArrayEquals(expected, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}