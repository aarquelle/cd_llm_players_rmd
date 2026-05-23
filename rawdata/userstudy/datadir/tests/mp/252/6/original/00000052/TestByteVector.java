import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);

        v.putUTF8("A\u0080\u0800B"); // 1 + 2 + 3 + 1 = 7 bytes, length prefix should be 0x0007
        v.putByteArray(null, 0, 3);  // append 3 zero bytes

        assertArrayEquals(
                new byte[] {
                        0x00, 0x07,
                        0x41,
                        (byte) 0xC2, (byte) 0x80,
                        (byte) 0xE0, (byte) 0xA0, (byte) 0x80,
                        0x42,
                        0x00, 0x00, 0x00
                },
                java.util.Arrays.copyOf(v.data, v.length)
        );
        assertEquals(12, v.length);
    }
}