import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.putByte(0x5A).putUTF8("\u0080\u0800"); // 2-byte + 3-byte => total UTF8 bytes = 5

        byte[] expected = new byte[] {
                (byte) 0x5A,
                0x00, 0x05,
                (byte) 0xC2, (byte) 0x80,
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80
        };

        assertArrayEquals(expected, java.util.Arrays.copyOf(v.data, v.length));
        assertEquals(expected.length, v.length);
    }
}