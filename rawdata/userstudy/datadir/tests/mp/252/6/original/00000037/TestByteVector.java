import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3); // force enlarge for non-ascii UTF8 (needs 2 + 3 = 5 bytes)
        bv.putUTF8("\u0800"); // 3-byte UTF8 encoding

        assertArrayEquals(new byte[] {0, 3, (byte) 0xE0, (byte) 0xA0, (byte) 0x80}, java.util.Arrays.copyOf(bv.data, bv.length));
        assertEquals(5, bv.length);
    }
}