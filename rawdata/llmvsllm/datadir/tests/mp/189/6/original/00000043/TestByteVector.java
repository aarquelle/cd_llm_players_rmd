import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putByte(0x7F).putUTF8("A\u00A2\u0800"); // 'A' (1), '¢' (2), '\u0800' (3) => 6 bytes UTF8

        assertEquals(1 + 2 + 6, bv.length);

        byte[] d = bv.data;
        assertArrayEquals(
                new byte[] {
                        0x7F,
                        0x00, 0x06,
                        0x41,
                        (byte) 0xC2, (byte) 0xA2,
                        (byte) 0xE0, (byte) 0xA0, (byte) 0x80
                },
                new byte[] { d[0], d[1], d[2], d[3], d[4], d[5], d[6], d[7], d[8] }
        );
    }
}