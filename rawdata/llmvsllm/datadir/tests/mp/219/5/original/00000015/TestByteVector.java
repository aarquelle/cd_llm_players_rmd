import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector();
        bv.putUTF8("Ā"); // U+0100 should be encoded as C4 80 in modified UTF-8 used here

        assertEquals(4, bv.length);
        assertArrayEquals(new byte[] {0, 2, (byte) 0xC4, (byte) 0x80},
                new byte[] {bv.data[0], bv.data[1], bv.data[2], bv.data[3]});
    }
}