import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        String s = "A\u0000\u0080\u07FF\u0800B";

        bv.putUTF8(s);

        byte[] utf = s.getBytes("UTF-8");
        byte[] expected = new byte[] {
                (byte) (utf.length >>> 8),
                (byte) utf.length,
                utf[0], utf[1], utf[2], utf[3], utf[4], utf[5], utf[6], utf[7], utf[8], utf[9], utf[10], utf[11]
        };

        byte[] actual = new byte[] {
                bv.data[0], bv.data[1], bv.data[2], bv.data[3], bv.data[4], bv.data[5], bv.data[6],
                bv.data[7], bv.data[8], bv.data[9], bv.data[10], bv.data[11], bv.data[12], bv.data[13]
        };

        assertEquals(expected.length, bv.length);
        assertArrayEquals(expected, actual);
    }
}