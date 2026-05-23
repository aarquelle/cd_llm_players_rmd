import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(4);
        bv.putUTF8("a\u0800b"); // 'a' (1 byte) + U+0800 (3 bytes) + 'b' (1 byte) => 5 bytes

        assertEquals(7, bv.length); // 2 length bytes + 5 payload bytes
        assertArrayEquals(new byte[] {0, 5, 97, (byte) 0xE0, (byte) 0xA0, (byte) 0x80, 98},
                java.util.Arrays.copyOf(bv.data, bv.length));
    }
}