import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2); // force enlarge path
        String s = "A\u0100B"; // UTF-8 bytes: 0x41, 0xC4 0x80, 0x42 ; byteLength=4

        bv.putUTF8(s);

        byte[] expected = new byte[] {
                0x00, 0x04,
                0x41,
                (byte) 0xC4, (byte) 0x80,
                0x42
        };

        assertEquals(expected.length, bv.length);
        byte[] actual = java.util.Arrays.copyOf(bv.data, bv.length);
        assertArrayEquals(expected, actual);
    }
}