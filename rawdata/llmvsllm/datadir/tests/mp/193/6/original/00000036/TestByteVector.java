import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(4);

        v.putByte(0x7F); // prefix to ensure internal offset is respected
        v.putUTF8("A\u0080\u0800"); // 1-byte, 2-byte, 3-byte => byteLength=6

        byte[] d = v.data;

        assertArrayEquals(new byte[] {
                0x7F,
                0x00, 0x06,                 // rewritten UTF8 byte length
                0x41,                       // 'A'
                (byte) 0xC2, (byte) 0x80,    // U+0080
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800
        }, new byte[] { d[0], d[1], d[2], d[3], d[4], d[5], d[6], d[7], d[8] });

        assertEquals(9, v.length);
    }
}