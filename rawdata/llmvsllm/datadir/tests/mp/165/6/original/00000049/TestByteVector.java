import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3); // force enlarge during encoding rewrite
        bv.putUTF8("\u0080"); // 2-byte UTF8 sequence; length header must be 0x0002

        byte[] expected = new byte[] { 0x00, 0x02, (byte) 0xC2, (byte) 0x80 };
        assertArrayEquals(expected, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}