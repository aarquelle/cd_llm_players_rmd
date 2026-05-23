import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(4);
        bv.putByte(0x7F);

        String s = "\u0000\u0080\u0800"; // encodes to 2 + (2 + 2 + 3) bytes, with explicit non-ASCII paths
        bv.putUTF8(s);

        assertArrayEquals(
                new byte[] {
                        0x7F,
                        0x00, 0x07,                 // UTF8 byte length = 7
                        (byte) 0xC0, (byte) 0x80,   // U+0000 -> 2 bytes
                        (byte) 0xC2, (byte) 0x80,   // U+0080 -> 2 bytes
                        (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800 -> 3 bytes
                },
                java.util.Arrays.copyOf(bv.data, bv.length)
        );
    }
}