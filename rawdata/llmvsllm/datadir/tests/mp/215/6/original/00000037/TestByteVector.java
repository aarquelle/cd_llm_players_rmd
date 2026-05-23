import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3);
        String s = "A\u0080\u0800B";

        bv.putUTF8(s);

        byte[] expectedPayload = s.getBytes(StandardCharsets.UTF_8);
        byte[] actual = Arrays.copyOf(bv.data, bv.length);

        assertEquals(2 + expectedPayload.length, bv.length);
        assertArrayEquals(expectedPayload, Arrays.copyOfRange(actual, 2, actual.length));
    }
}