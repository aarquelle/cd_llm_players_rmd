import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(3); // force enlargement path
        v.putUTF8("A\u0080\u0800");

        Field dataF = ByteVector.class.getDeclaredField("data");
        dataF.setAccessible(true);
        byte[] data = (byte[]) dataF.get(v);

        Field lenF = ByteVector.class.getDeclaredField("length");
        lenF.setAccessible(true);
        int len = (Integer) lenF.get(v);

        byte[] expected = new byte[] {
            0x00, 0x06,                   // modified UTF-8 byte length = 6
            0x41,                         // 'A'
            (byte) 0xC2, (byte) 0x80,      // U+0080
            (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800
        };

        assertEquals(expected.length, len);
        assertArrayEquals(expected, java.util.Arrays.copyOf(data, len));
    }
}