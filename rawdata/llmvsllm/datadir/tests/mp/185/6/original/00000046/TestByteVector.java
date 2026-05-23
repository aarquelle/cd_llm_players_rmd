import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.putByte(0x7F);
        v.putUTF8("A\u0800");

        java.lang.reflect.Field dataF = ByteVector.class.getDeclaredField("data");
        dataF.setAccessible(true);
        byte[] data = (byte[]) dataF.get(v);

        java.lang.reflect.Field lenF = ByteVector.class.getDeclaredField("length");
        lenF.setAccessible(true);
        int len = (Integer) lenF.get(v);

        assertEquals(1 + 2 + 4, len);
        assertArrayEquals(new byte[] { 0x7F, 0x00, 0x04, 0x41, (byte) 0xE0, (byte) 0xA0, (byte) 0x80 },
                java.util.Arrays.copyOf(data, len));
    }
}