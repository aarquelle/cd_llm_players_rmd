import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3); // force enlarge: needs 2 (len) + 3 (utf8 bytes) = 5
        bv.putUTF8("\u0800"); // 3-byte UTF8 sequence

        assertEquals(5, bv.length);
        assertArrayEquals(new byte[] { 0, 3, (byte) 0xE0, (byte) 0xA0, (byte) 0x80 }, new byte[] {
                bv.data[0], bv.data[1], bv.data[2], bv.data[3], bv.data[4]
        });
    }
}