import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        String s = "A\u00E9B";
        bv.putUTF8(s);

        byte[] utf8 = s.getBytes("UTF-8");

        assertEquals(2 + utf8.length, bv.length);
        assertArrayEquals(
                new byte[] { 0, (byte) utf8.length, utf8[0], utf8[1], utf8[2], utf8[3] },
                new byte[] { bv.data[0], bv.data[1], bv.data[2], bv.data[3], bv.data[4], bv.data[5] }
        );
    }
}