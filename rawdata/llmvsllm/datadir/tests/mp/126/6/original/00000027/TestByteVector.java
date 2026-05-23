import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1); // force enlarge paths
        String s = "A\u00A2\u0800"; // 1-byte + 2-byte + 3-byte => total 6 bytes

        bv.putUTF8(s);

        byte[] actual = Arrays.copyOf(bv.data, bv.length);
        byte[] expected = new byte[] {0, 6, 0x41, (byte) 0xC2, (byte) 0xA2, (byte) 0xE0, (byte) 0xA0, (byte) 0x80};

        assertEquals(expected.length, bv.length);
        assertArrayEquals(expected, actual);
    }
}