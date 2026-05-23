import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                String s = "A\u0001\u0080\u0800Z";
        ByteVector bv = new ByteVector(2);

        bv.putUTF8(s);

        byte[] payload = s.getBytes(StandardCharsets.UTF_8);
        int n = payload.length;

        byte[] expected = new byte[2 + n];
        expected[0] = (byte) (n >>> 8);
        expected[1] = (byte) n;
        expected[2] = payload[0];
        expected[3] = payload[1];
        expected[4] = payload[2];
        expected[5] = payload[3];
        expected[6] = payload[4];
        expected[7] = payload[5];
        expected[8] = payload[6];
        expected[9] = payload[7];
        expected[10] = payload[8];

        assertEquals(expected.length, bv.length);
        assertArrayEquals(expected, Arrays.copyOf(bv.data, bv.length));
    }
}