import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);
        String s = "A\u0080\u0800";
        bv.putUTF8(s);

        byte[] utf8 = s.getBytes(StandardCharsets.UTF_8);
        byte[] expected = new byte[] {
                0, (byte) utf8.length,
                utf8[0], utf8[1], utf8[2], utf8[3], utf8[4], utf8[5]
        };

        byte[] actual = new byte[] {
                bv.data[0], bv.data[1], bv.data[2], bv.data[3],
                bv.data[4], bv.data[5], bv.data[6], bv.data[7]
        };

        assertEquals(8, bv.length);
        assertArrayEquals(expected, actual);
    }
}