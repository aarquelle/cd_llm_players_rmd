import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector();
        bv.putUTF8("\u0080"); // U+0080 should be encoded as C2 80 in modified UTF-8.

        byte[] data = bv.data;
        assertEquals(4, bv.length);
        assertArrayEquals(new byte[] {0, 2, (byte) 0xC2, (byte) 0x80}, new byte[] {data[0], data[1], data[2], data[3]});
    }
}