import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putByte(0x7F); // ensure prefix update uses absolute indices (length, length+1)

        bv.putUTF8("a\u0080\u0800"); // 'a' (1), U+0080 (2), U+0800 (3) => 6 bytes

        assertEquals(1 + 2 + 6, bv.length);

        int idx = 1;
        assertArrayEquals(new byte[] {
                (byte) 0x00, (byte) 0x06,             // UTF8 byte length = 6
                (byte) 'a',                           // 0x61
                (byte) 0xC2, (byte) 0x80,             // U+0080
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800
        }, java.util.Arrays.copyOfRange(bv.data, idx, idx + 8));
    }
}