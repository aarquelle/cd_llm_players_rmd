import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(4);

        v.putByte(0x7F);              // force non-zero start offset
        v.putUTF8("A\u07FF\u0800");    // 1 + 2 + 3 bytes => 6, charLength=3

        java.lang.reflect.Field dataF = ByteVector.class.getDeclaredField("data");
        dataF.setAccessible(true);
        byte[] data = (byte[]) dataF.get(v);

        java.lang.reflect.Field lenF = ByteVector.class.getDeclaredField("length");
        lenF.setAccessible(true);
        int len = (Integer) lenF.get(v);

        assertEquals(1 + 2 + 6, len);
        assertArrayEquals(new byte[] {
                (byte) 0x7F,
                0x00, 0x06,
                0x41,
                (byte) 0xDF, (byte) 0xBF,
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80
        }, java.util.Arrays.copyOf(data, len));
    }
}