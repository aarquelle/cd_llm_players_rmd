import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.putUTF8("A\u0800"); // 'A' (1 byte) + U+0800 (3 bytes) => byteLength=4

        assertEquals(6, v.length); // 2 bytes length prefix + 4 bytes payload

        byte[] d = v.data;
        assertArrayEquals(new byte[] {
                0x00, 0x04,                   // UTF8 byte length = 4
                0x41,                         // 'A'
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800
        }, new byte[] { d[0], d[1], d[2], d[3], d[4], d[5] });
    }
}