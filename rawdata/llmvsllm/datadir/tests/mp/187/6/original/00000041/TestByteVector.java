import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(4);
        bv.putByte(0x55).putUTF8("A\u0800B"); // 'A' (1), U+0800 (3), 'B' (1) => 5 bytes, length prefix = 0x0005

        assertEquals(1 + 2 + 5, bv.length);

        int p = 1;
        assertArrayEquals(new byte[] {
                (byte) 0x55,
                0x00, 0x05,
                0x41,
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80,
                0x42
        }, java.util.Arrays.copyOf(bv.data, p + 2 + 5));
    }
}