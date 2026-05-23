import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(4);
        bv.putByte(0x7F); // sentinel before UTF8 payload
        bv.putUTF8("\u00E9\u0800"); // 'é' => 2 bytes, U+0800 => 3 bytes, total 5

        assertEquals(1 + 2 + 5, bv.length);

        byte[] expected = new byte[] {
                0x7F,
                0x00, 0x05,
                (byte) 0xC3, (byte) 0xA9,
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80
        };
        assertArrayEquals(expected, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}