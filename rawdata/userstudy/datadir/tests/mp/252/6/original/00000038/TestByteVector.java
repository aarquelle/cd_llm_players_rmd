import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);
        bv.putByte(0x7F).putUTF8("A\u0080\u0800");

        assertEquals(1 + 2 + 1 + 2 + 3, bv.length);

        int start = 1;
        assertArrayEquals(
                new byte[] {
                        0x00, 0x06,
                        0x41,
                        (byte) 0xC2, (byte) 0x80,
                        (byte) 0xE0, (byte) 0xA0, (byte) 0x80
                },
                new byte[] {
                        bv.data[start], bv.data[start + 1],
                        bv.data[start + 2],
                        bv.data[start + 3], bv.data[start + 4],
                        bv.data[start + 5], bv.data[start + 6], bv.data[start + 7]
                }
        );
    }
}