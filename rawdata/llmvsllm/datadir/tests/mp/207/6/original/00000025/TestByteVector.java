import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3); // force enlargement paths
        String s = "A\u0080\u0800"; // ASCII (1), two-byte (2), three-byte (3) => 6 bytes
        bv.putUTF8(s);

        assertEquals(8, bv.length); // 2-byte length prefix + 6 bytes payload
        assertArrayEquals(
                new byte[] {
                        0x00, 0x06,                 // byte length = 6
                        0x41,                       // 'A'
                        (byte) 0xC2, (byte) 0x80,   // U+0080
                        (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800
                },
                java.util.Arrays.copyOf(bv.data, bv.length)
        );
    }
}