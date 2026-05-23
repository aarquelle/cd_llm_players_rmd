import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector();
        bv.putUTF8("\u0100"); // U+0100 should be encoded as 2 bytes: C4 80, with length prefix 0x0002
        assertEquals(4, bv.length);
        assertArrayEquals(new byte[] {0x00, 0x02, (byte) 0xC4, (byte) 0x80}, bv.data);
    }
}