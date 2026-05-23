import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.putByte(0x55); // sentinel before UTF8 data
        v.putUTF8("A\u0080\u0800"); // 1 + 2 + 3 = 6 bytes of UTF8 payload

        java.lang.reflect.Field dataF = ByteVector.class.getDeclaredField("data");
        java.lang.reflect.Field lenF = ByteVector.class.getDeclaredField("length");
        dataF.setAccessible(true);
        lenF.setAccessible(true);
        byte[] data = (byte[]) dataF.get(v);
        int length = (Integer) lenF.get(v);

        assertEquals(10, length); // 1 + 2 + 6
        assertArrayEquals(new byte[] {
                (byte) 0x55,
                0x00, 0x06,
                0x41,
                (byte) 0xC2, (byte) 0x80,
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80
        }, java.util.Arrays.copyOfRange(data, 0, length));
    }
}