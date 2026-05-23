import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2); // force enlarge path
        bv.putUTF8("A\u0000\u0800"); // 'A' (1 byte), NUL (2 bytes), U+0800 (3 bytes) => 6 bytes payload

        byte[] d = bv.data;

        assertArrayEquals(new byte[] {
                0x00, 0x06,       // computed byte length
                0x41,             // 'A'
                (byte) 0xC0, (byte) 0x80,             // U+0000
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80  // U+0800
        }, java.util.Arrays.copyOf(d, bv.length));
        assertEquals(8, bv.length);
    }
}