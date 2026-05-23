import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2); // force enlarge path
        String s = "A\u0000\u0080\u0800";   // 1-byte, 2-byte, 2-byte, 3-byte => total 8 bytes
        bv.putUTF8(s);

        byte[] expectedPayload = s.getBytes(StandardCharsets.UTF_8);
        byte[] actual = Arrays.copyOf(bv.data, bv.length);

        assertArrayEquals(new byte[] {0, (byte) expectedPayload.length}, new byte[] {actual[0], actual[1]});
        assertArrayEquals(expectedPayload, Arrays.copyOfRange(actual, 2, actual.length));
    }
}