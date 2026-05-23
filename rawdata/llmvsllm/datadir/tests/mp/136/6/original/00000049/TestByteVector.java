import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(4);
        bv.putByte(0x55); // sentinel before UTF8
        bv.putUTF8("A\u0080\u0800"); // 1 + 2 + 3 = 6 bytes

        assertEquals(1 + 2 + 6, bv.length);

        byte[] d = bv.data;
        assertArrayEquals(
                new byte[] {
                        (byte) 0x55,
                        0x00, 0x06,
                        0x41,
                        (byte) 0xC2, (byte) 0x80,
                        (byte) 0xE0, (byte) 0xA0, (byte) 0x80
                },
                new byte[] { d[0], d[1], d[2], d[3], d[4], d[5], d[6], d[7], d[8] }
        );
    }
}