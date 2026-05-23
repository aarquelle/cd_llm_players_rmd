import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(3); // force enlarge when writing UTF8 (needs 5 bytes total)
        v.putUTF8("\u0080"); // 2-byte UTF8, length prefix should be 0x0002

        assertArrayEquals(new byte[] {0, 2, (byte) 0xC2, (byte) 0x80}, Arrays.copyOf(v.data, v.length));
        assertEquals(4, v.length);
    }
}