import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(4);
        bv.putByte(0x7F);

        String s = "A" + '\u07FF' + '\u0800';
        bv.putUTF8(s);

        java.lang.reflect.Field dataF = ByteVector.class.getDeclaredField("data");
        dataF.setAccessible(true);
        byte[] data = (byte[]) dataF.get(bv);

        java.lang.reflect.Field lenF = ByteVector.class.getDeclaredField("length");
        lenF.setAccessible(true);
        int length = (Integer) lenF.get(bv);

        byte[] expected = new byte[] {
                (byte) 0x7F,
                0x00, 0x06,                 // UTF8 byte length = 1 + 2 + 3 = 6
                0x41,                       // 'A'
                (byte) 0xDF, (byte) 0xBF,   // U+07FF
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800
        };

        assertArrayEquals(expected, java.util.Arrays.copyOf(data, length));
        assertEquals(expected.length, length);
    }
}