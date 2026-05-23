import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(4);
        String s = "A\u0000\u07FF\u0800"; // 1 + 2 + 2 + 3 bytes = 8 bytes payload
        bv.putByte(0x5A).putUTF8(s).putShort(0xBEEF);

        java.lang.reflect.Field dataF = ByteVector.class.getDeclaredField("data");
        java.lang.reflect.Field lenF = ByteVector.class.getDeclaredField("length");
        dataF.setAccessible(true);
        lenF.setAccessible(true);
        byte[] data = (byte[]) dataF.get(bv);
        int len = (Integer) lenF.get(bv);

        byte[] expected = new byte[] {
            (byte)0x5A,
            0x00, 0x08,
            0x41,
            (byte)0xC0, (byte)0x80,
            (byte)0xDF, (byte)0xBF,
            (byte)0xE0, (byte)0xA0, (byte)0x80,
            (byte)0xBE, (byte)0xEF
        };

        assertEquals(expected.length, len);
        assertArrayEquals(expected, java.util.Arrays.copyOf(data, len));
    }
}