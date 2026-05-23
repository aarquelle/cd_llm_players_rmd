import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(3); // force enlarge on UTF8 write
        v.putUTF8("A\u00A2\u0800"); // 'A' (1), '¢' (2), U+0800 (3) => byteLength 6

        java.lang.reflect.Field dataF = ByteVector.class.getDeclaredField("data");
        java.lang.reflect.Field lenF = ByteVector.class.getDeclaredField("length");
        dataF.setAccessible(true);
        lenF.setAccessible(true);

        byte[] data = (byte[]) dataF.get(v);
        int len = (Integer) lenF.get(v);

        assertEquals(8, len); // 2-byte length prefix + 6 UTF8 bytes

        byte[] expected = new byte[] {
            0x00, 0x06,                         // byte length
            0x41,                               // 'A'
            (byte) 0xC2, (byte) 0xA2,           // '¢'
            (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800
        };
        assertArrayEquals(expected, java.util.Arrays.copyOf(data, len));
    }
}