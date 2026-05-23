import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1); // force enlarge path
        String s = "A\u00A2\u0800"; // 1-byte + 2-byte + 3-byte => 6 bytes
        bv.putUTF8(s);

        byte[] expected = new byte[] {
            0x00, 0x06,
            0x41,
            (byte) 0xC2, (byte) 0xA2,
            (byte) 0xE0, (byte) 0xA0, (byte) 0x80
        };

        assertArrayEquals(expected, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}