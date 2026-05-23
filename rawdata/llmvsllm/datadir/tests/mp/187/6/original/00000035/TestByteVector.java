import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2); // force enlarge in putUTF8
        bv.putByte(0xAB);
        bv.putUTF8("A\u0080\u0800"); // 'A' (1), U+0080 (2), U+0800 (3) => 6 bytes

        assertEquals(1 + 2 + 6, bv.length);

        byte[] expected = new byte[] {
                (byte) 0xAB,
                0x00, 0x06,
                0x41,
                (byte) 0xC2, (byte) 0x80,
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80
        };
        assertArrayEquals(expected, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}