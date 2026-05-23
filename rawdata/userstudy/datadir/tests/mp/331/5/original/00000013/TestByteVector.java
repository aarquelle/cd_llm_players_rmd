import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector();
        bv.putUTF8("\u00E9"); // 'é' U+00E9 should be encoded as 0xC3 0xA9 in UTF-8

        assertEquals(4, bv.length); // 2 length bytes + 2 UTF-8 bytes
        assertEquals((byte) 0xC3, bv.data[2]);
    }
}