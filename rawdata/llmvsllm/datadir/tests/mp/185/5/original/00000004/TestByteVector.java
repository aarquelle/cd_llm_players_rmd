import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector();
        bv.putUTF8("a\u0800b"); // 'a', U+0800 (3-byte UTF-8), 'b'

        assertEquals(7, bv.length); // 2 (len) + 1 + 3 + 1
        assertArrayEquals(new byte[] {0, 5, 0x61, (byte) 0xE0, (byte) 0xA0, (byte) 0x80, 0x62},
                java.util.Arrays.copyOf(bv.data, bv.length));
    }
}