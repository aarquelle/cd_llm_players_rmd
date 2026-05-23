import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);
        String s = "A\u0000\u0800"; // 'A' -> 1 byte, '\u0000' -> 2 bytes, '\u0800' -> 3 bytes => 6 bytes total
        bv.putUTF8(s);

        assertEquals(8, bv.length);
        assertArrayEquals(new byte[] {0, 6, 0x41, (byte) 0xC0, (byte) 0x80, (byte) 0xE0, (byte) 0xA0, (byte) 0x80},
                java.util.Arrays.copyOf(bv.data, bv.length));
    }
}