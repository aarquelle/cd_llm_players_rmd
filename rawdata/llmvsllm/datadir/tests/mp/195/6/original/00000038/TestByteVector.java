import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(3);
        v.putByte(0x7F);
        v.putUTF8("A\u0080\u0800");

        java.lang.reflect.Field dataF = ByteVector.class.getDeclaredField("data");
        dataF.setAccessible(true);
        byte[] d = (byte[]) dataF.get(v);

        assertArrayEquals(
                new byte[] {
                        0x7F,
                        0x00, 0x06,
                        0x41,
                        (byte) 0xC2, (byte) 0x80,
                        (byte) 0xE0, (byte) 0xA0, (byte) 0x80
                },
                java.util.Arrays.copyOf(d, 9)
        );

        java.lang.reflect.Field lenF = ByteVector.class.getDeclaredField("length");
        lenF.setAccessible(true);
        assertEquals(9, lenF.getInt(v));
    }
}