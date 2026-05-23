import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);
        String s = "A\u0080\u0800"; // 1 byte + 2 bytes + 3 bytes = 6 bytes in modified UTF-8 here
        bv.putUTF8(s);

        byte[] expected = new byte[] {0, 6, 0x41, (byte) 0xC2, (byte) 0x80, (byte) 0xE0, (byte) 0xA0, (byte) 0x80};

        assertEquals(expected.length, bv.length);
        assertArrayEquals(expected, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}