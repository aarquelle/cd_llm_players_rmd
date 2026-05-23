import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(4);
        String s = "A\u0080\u0800B";

        bv.putUTF8(s);

        byte[] utf8 = s.getBytes(Charset.forName("UTF-8"));
        int utfLen = utf8.length;

        byte[] expected = new byte[2 + utfLen];
        expected[0] = (byte) (utfLen >>> 8);
        expected[1] = (byte) utfLen;
        expected[2] = utf8[0];
        expected[3] = utf8[1];
        expected[4] = utf8[2];
        expected[5] = utf8[3];
        expected[6] = utf8[4];
        expected[7] = utf8[5];
        expected[8] = utf8[6];

        assertEquals(expected.length, bv.length);
        assertArrayEquals(expected, Arrays.copyOf(bv.data, bv.length));
    }
}