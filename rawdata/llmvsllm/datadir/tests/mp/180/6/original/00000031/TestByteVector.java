import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putByte(0x7F); // force length=1 to exercise backpatch at non-zero offset
        bv.putUTF8("A\u0800"); // 'A' (1 byte) + U+0800 (3 bytes) => 4 bytes

        assertEquals(1 + 2 + 4, bv.length);

        int offset = 1; // start of UTF8 length prefix
        assertArrayEquals(
                new byte[] {
                        0x7F,
                        0x00, 0x04,             // modified UTF8 byte length
                        0x41,                   // 'A'
                        (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800
                },
                java.util.Arrays.copyOf(bv.data, offset + 2 + 4)
        );
    }
}