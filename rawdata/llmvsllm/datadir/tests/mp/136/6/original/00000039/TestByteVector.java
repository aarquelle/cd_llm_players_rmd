import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3); // force enlarge during UTF8 write
        bv.putUTF8("A\u0000\u0800"); // 'A' (1 byte), NUL (2 bytes), U+0800 (3 bytes) => 6 bytes, length prefix 0x0006

        byte[] expected = new byte[] {
                0x00, 0x06,
                0x41,
                (byte) 0xC0, (byte) 0x80,
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80
        };

        assertEquals(8, bv.length);
        assertArrayEquals(expected, Arrays.copyOf(bv.data, bv.length));
    }
}