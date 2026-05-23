import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);

        String s = "A\u0000\u0800B";
        bv.putUTF8(s);

        byte[] expected = new byte[] {
            0x00, 0x08,                   // byte length = 8
            0x41,                         // 'A'
            (byte) 0xC0, (byte) 0x80,      // U+0000
            (byte) 0xE0, (byte) 0xA0, (byte) 0x80, // U+0800
            0x42                          // 'B'
        };

        java.lang.reflect.Field dataF = ByteVector.class.getDeclaredField("data");
        java.lang.reflect.Field lenF = ByteVector.class.getDeclaredField("length");
        dataF.setAccessible(true);
        lenF.setAccessible(true);

        byte[] data = (byte[]) dataF.get(bv);
        int len = (int) lenF.get(bv);

        assertAll(
            () -> assertEquals(expected.length, len),
            () -> assertArrayEquals(expected, java.util.Arrays.copyOf(data, len))
        );
    }
}