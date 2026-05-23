import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector();
        bv.putUTF8("\u007F"); // DEL should be encoded as 2-byte UTF-8 sequence

        assertEquals(4, bv.length);
        assertArrayEquals(new byte[] {0, 2, (byte) 0xC1, (byte) 0xBF}, new byte[] {bv.data[0], bv.data[1], bv.data[2], bv.data[3]});
    }
}