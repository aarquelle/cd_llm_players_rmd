import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3); // force enlarge path
        String s = "A\u0080\u0800"; // 1-byte + 2-byte + 3-byte UTF8 => 6 bytes, header should be 0x0006

        bv.putUTF8(s);

        assertArrayEquals(
                new byte[] {0x00, 0x06, 0x41, (byte) 0xC2, (byte) 0x80, (byte) 0xE0, (byte) 0xA0, (byte) 0x80},
                java.util.Arrays.copyOf(bv.data, bv.length)
        );
        assertEquals(8, bv.length);
    }
}