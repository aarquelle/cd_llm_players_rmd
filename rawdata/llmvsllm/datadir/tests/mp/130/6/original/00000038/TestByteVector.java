import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3); // force enlarge when writing
        bv.putUTF8("\u0080"); // U+0080 encodes as 2 bytes in modified UTF-8 style used here

        assertEquals(4, bv.length); // 2 bytes length header + 2 bytes data
        assertArrayEquals(new byte[] { 0, 2, (byte) 0xC2, (byte) 0x80 }, new byte[] { bv.data[0], bv.data[1], bv.data[2], bv.data[3] });
    }
}