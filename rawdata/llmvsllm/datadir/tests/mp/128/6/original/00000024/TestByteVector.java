import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);
        String s = "A\u0000\u0080\u0800B";

        bv.putUTF8(s);

        byte[] payload = s.getBytes(StandardCharsets.UTF_8);
        byte[] expected = new byte[2 + payload.length];
        expected[0] = (byte) (payload.length >>> 8);
        expected[1] = (byte) payload.length;
        byte[] expectedFull = Arrays.copyOf(expected, expected.length);
        byte[] payloadFull = Arrays.copyOf(payload, payload.length);
        byte[] actual = Arrays.copyOf(bv.data, bv.length);

        expectedFull = new byte[] {
                expectedFull[0], expectedFull[1],
                payloadFull[0], payloadFull[1], payloadFull[2], payloadFull[3], payloadFull[4],
                payloadFull[5], payloadFull[6], payloadFull[7], payloadFull[8], payloadFull[9]
        };

        assertEquals(expectedFull.length, bv.length);
        assertArrayEquals(expectedFull, actual);
    }
}