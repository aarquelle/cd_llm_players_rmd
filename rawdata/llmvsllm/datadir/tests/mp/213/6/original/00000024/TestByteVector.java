import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                String s = "A\u00A2\u0800";
        ByteVector bv = new ByteVector(1);
        bv.putUTF8(s);

        byte[] p = s.getBytes(StandardCharsets.UTF_8);
        byte[] expected = new byte[] {
                (byte) (p.length >>> 8),
                (byte) p.length,
                p[0], p[1], p[2], p[3], p[4], p[5]
        };

        byte[] actual = new byte[] {
                bv.data[0], bv.data[1], bv.data[2], bv.data[3],
                bv.data[4], bv.data[5], bv.data[6], bv.data[7]
        };

        assertArrayEquals(expected, actual);
        assertEquals(8, bv.length);
    }
}