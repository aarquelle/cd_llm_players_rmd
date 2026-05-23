import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);
        String s = "A\u00E9\u0800"; // UTF-8 bytes: 1 + 2 + 3 = 6

        bv.putUTF8(s);

        byte[] payload = s.getBytes("UTF-8");
        byte[] expected = new byte[] {
                0, (byte) payload.length,
                payload[0], payload[1], payload[2], payload[3], payload[4], payload[5]
        };

        assertEquals(expected.length, bv.length);
        assertArrayEquals(expected, new byte[] {
                bv.data[0], bv.data[1], bv.data[2], bv.data[3],
                bv.data[4], bv.data[5], bv.data[6], bv.data[7]
        });
    }
}