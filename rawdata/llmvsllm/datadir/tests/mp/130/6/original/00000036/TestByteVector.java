import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3);
        bv.putByte(0x7F).putUTF8("A\u0080\u0800"); // 'A' (1), U+0080 (2), U+0800 (3) => 6 bytes

        java.lang.reflect.Field dataF = ByteVector.class.getDeclaredField("data");
        java.lang.reflect.Field lenF = ByteVector.class.getDeclaredField("length");
        dataF.setAccessible(true);
        lenF.setAccessible(true);
        byte[] data = (byte[]) dataF.get(bv);
        int length = (Integer) lenF.get(bv);

        assertEquals(1 + 2 + 6, length);

        byte[] expected = new byte[] {
            (byte) 0x7F,
            0x00, 0x06,
            0x41,
            (byte) 0xC2, (byte) 0x80,
            (byte) 0xE0, (byte) 0xA0, (byte) 0x80
        };
        assertArrayEquals(expected, Arrays.copyOf(data, length));
    }
}