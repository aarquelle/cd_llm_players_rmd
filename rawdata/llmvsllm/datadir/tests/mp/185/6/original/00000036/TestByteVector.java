import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1); // force enlarge path inside putUTF8
        v.putUTF8("A\u07FF\u0800"); // 'A' (1 byte), U+07FF (2 bytes), U+0800 (3 bytes) => 6 bytes

        assertEquals(8, v.length); // 2 length bytes + 6 payload bytes

        byte[] d = v.data;
        assertArrayEquals(new byte[] {
                0x00, 0x06,                   // UTF8 byte length = 6
                0x41,                         // 'A'
                (byte) 0xDF, (byte) 0xBF,     // U+07FF
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800
        }, new byte[] { d[0], d[1], d[2], d[3], d[4], d[5], d[6], d[7] });
    }
}