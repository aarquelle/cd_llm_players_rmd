import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(3);

        v.putByte(0x12);
        v.putUTF8("\u0080\u0800"); // 2 chars, UTF8 byte length = 2 + 3 = 5, total added = 7 -> forces enlarge
        v.putShort(0xABCD);

        assertEquals(10, v.length);
        assertArrayEquals(new byte[] {
                0x12,
                0x00, 0x05,
                (byte) 0xC2, (byte) 0x80,
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80,
                (byte) 0xAB, (byte) 0xCD
        }, java.util.Arrays.copyOf(v.data, v.length));
    }
}