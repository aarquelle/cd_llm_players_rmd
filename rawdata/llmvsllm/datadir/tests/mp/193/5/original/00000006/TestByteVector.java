import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector();
        bv.putUTF8("\u007F"); // DEL, should be encoded as 1 byte in modified UTF-8

        assertEquals(3, bv.length);
        assertArrayEquals(new byte[] {0, 1, 0x7F}, new byte[] {bv.data[0], bv.data[1], bv.data[2]});
    }
}