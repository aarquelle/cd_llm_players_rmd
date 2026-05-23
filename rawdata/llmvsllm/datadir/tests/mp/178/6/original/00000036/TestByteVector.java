import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        String s = "A\u0800B"; // 'A' (1 byte), U+0800 (3 bytes), 'B' (1 byte) => 5 bytes, charLength=3
        bv.putUTF8(s);

        assertEquals(7, bv.length); // 2-byte length prefix + 5 UTF-8 bytes
        assertArrayEquals(new byte[] {0, 5, 0x41, (byte) 0xE0, (byte) 0xA0, (byte) 0x80, 0x42},
                java.util.Arrays.copyOf(bv.data, bv.length));
    }
}