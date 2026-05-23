import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putUTF8("\u0080\u0800"); // 2 chars, UTF-8 bytes: C2 80 E0 A0 80, length prefix: 00 05

        assertArrayEquals(new byte[] {0, 5, (byte) 0xC2, (byte) 0x80, (byte) 0xE0, (byte) 0xA0, (byte) 0x80},
                java.util.Arrays.copyOf(bv.data, bv.length));
        assertEquals(7, bv.length);
    }
}