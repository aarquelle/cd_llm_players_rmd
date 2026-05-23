import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3);
        bv.putByte(0x7F).putUTF8("A\u0000\u0800");

        assertEquals(1 + 2 + 1 + 2 + 3, bv.length);

        int off = 1;
        assertArrayEquals(new byte[] {
                0x00, 0x06, // UTF8 byte length = 6
                0x41,       // 'A'
                (byte) 0xC0, (byte) 0x80,             // U+0000
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80  // U+0800
        }, java.util.Arrays.copyOfRange(bv.data, off, off + 2 + 6));
    }
}