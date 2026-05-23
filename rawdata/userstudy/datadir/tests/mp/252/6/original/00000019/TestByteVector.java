import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(4);
        bv.putUTF8("A\u00A2\u0800"); // 'A' (1 byte), '¢' (2 bytes), '\u0800' (3 bytes) => 6 bytes

        assertEquals(8, bv.length); // 2 length bytes + 6 UTF8 bytes

        byte[] d = bv.data;
        assertArrayEquals(new byte[] {
                0x00, 0x06,              // byte length = 6
                0x41,                    // 'A'
                (byte) 0xC2, (byte) 0xA2,// '¢'
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // '\u0800'
        }, new byte[] { d[0], d[1], d[2], d[3], d[4], d[5], d[6], d[7] });
    }
}