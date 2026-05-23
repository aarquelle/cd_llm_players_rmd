import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);
        String s = "A\u0000\u0800B"; // 'A'(1) + NUL(2) + U+0800(3) + 'B'(1) => 7 bytes
        bv.putUTF8(s);

        assertArrayEquals(
                new byte[] {0, 7, 0x41, (byte) 0xC0, (byte) 0x80, (byte) 0xE0, (byte) 0xA0, (byte) 0x80, 0x42},
                java.util.Arrays.copyOf(bv.data, bv.length)
        );
        assertEquals(9, bv.length);
    }
}