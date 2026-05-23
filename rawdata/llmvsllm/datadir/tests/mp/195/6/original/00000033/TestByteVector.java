import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putUTF8("A\u0080\u0800"); // 'A' -> 1 byte, U+0080 -> 2 bytes, U+0800 -> 3 bytes => 6 bytes total

        assertEquals(8, bv.length); // 2-byte length prefix + 6 bytes payload
        assertArrayEquals(new byte[] {
                0x00, 0x06,             // UTF8 byte length
                0x41,                   // 'A'
                (byte) 0xC2, (byte) 0x80, // U+0080
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800
        }, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}