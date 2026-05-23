import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);
        String s = "A\u0000\u0080\u07FF\u0800";
        byte[] expected = s.getBytes(StandardCharsets.UTF_8);

        bv.putUTF8(s);

        int headerLen = ((bv.data[0] & 0xFF) << 8) | (bv.data[1] & 0xFF);
        assertAll(
                () -> assertEquals(expected.length, headerLen),
                () -> assertArrayEquals(expected, java.util.Arrays.copyOfRange(bv.data, 2, 2 + headerLen))
        );
    }
}