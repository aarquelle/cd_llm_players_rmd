import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);

        bv.putByte(0x7F)
          .putUTF8("A\u0080\u0800"); // 1 + 2 + 3 bytes, length prefix should be 0x0006

        int idx = 1;
        assertArrayEquals(
                new byte[] {
                        0x7F,
                        0x00, 0x06,
                        0x41,
                        (byte) 0xC2, (byte) 0x80,
                        (byte) 0xE0, (byte) 0xA0, (byte) 0x80
                },
                java.util.Arrays.copyOf(bv.data, idx + 2 + 6)
        );

        int before = bv.length;
        bv.putByteArray(null, 0, 3);
        assertEquals(before + 3, bv.length);
    }
}