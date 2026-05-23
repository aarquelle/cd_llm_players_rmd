import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(4);
        v.putByte(0x55); // sentinel to verify correct offset handling
        v.putUTF8("A\u0080\u0800"); // 1-byte, 2-byte, 3-byte => total 6 bytes

        assertEquals(1 + 2 + 6, v.length);

        byte[] d = v.data;
        int o = 1; // after sentinel
        assertArrayEquals(
                new byte[] {
                        0x55,
                        0x00, 0x06,
                        0x41,
                        (byte) 0xC2, (byte) 0x80,
                        (byte) 0xE0, (byte) 0xA0, (byte) 0x80
                },
                new byte[] { d[0], d[o], d[o + 1], d[o + 2], d[o + 3], d[o + 4], d[o + 5], d[o + 6], d[o + 7], d[o + 8] }
        );
    }
}