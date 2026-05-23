import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector();
        bv.putUTF8("é"); // U+00E9 -> UTF-8: C3 A9, length 2

        assertEquals(4, bv.length); // 2 bytes length prefix + 2 bytes data
        assertArrayEquals(new byte[] {0, 2, (byte) 0xC3, (byte) 0xA9}, new byte[] {bv.data[0], bv.data[1], bv.data[2], bv.data[3]});
    }
}