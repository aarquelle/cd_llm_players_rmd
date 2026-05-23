import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putUTF8("\u0080"); // U+0080 encodes as 0xC2 0x80 in modified UTF-8 algorithm used here

        assertArrayEquals(new byte[] { 0x00, 0x02, (byte) 0xC2, (byte) 0x80 },
                java.util.Arrays.copyOf(bv.data, bv.length));
        assertEquals(4, bv.length);
    }
}