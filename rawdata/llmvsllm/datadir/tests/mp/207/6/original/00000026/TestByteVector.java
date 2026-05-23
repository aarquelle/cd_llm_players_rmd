import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1); // force enlargements
        String s = "A\u0000\u0080\u07FF\u0800"; // 1 + 2 + 2 + 2 + 3 = 10 bytes
        bv.putUTF8(s);

        var dataField = ByteVector.class.getDeclaredField("data");
        dataField.setAccessible(true);
        byte[] data = (byte[]) dataField.get(bv);

        byte[] expected = new byte[] {
                0x00, 0x0A,                         // UTF8 byte length = 10
                0x41,                               // 'A'
                (byte) 0xC0, (byte) 0x80,           // U+0000
                (byte) 0xC2, (byte) 0x80,           // U+0080
                (byte) 0xDF, (byte) 0xBF,           // U+07FF
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800
        };

        assertEquals(expected.length, bv.length);
        assertArrayEquals(expected, java.util.Arrays.copyOf(data, bv.length));
    }
}