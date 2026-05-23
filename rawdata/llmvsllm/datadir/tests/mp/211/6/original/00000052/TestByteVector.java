import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(3);
        v.putByte(0x7F);
        v.putUTF8("A\u0800");

        java.lang.reflect.Field dataF = ByteVector.class.getDeclaredField("data");
        dataF.setAccessible(true);
        byte[] d = (byte[]) dataF.get(v);

        byte[] prefix = new byte[] { d[0], d[1], d[2], d[3], d[4], d[5], d[6] };

        assertEquals(7, v.length);
        assertArrayEquals(new byte[] { 0x7F, 0x00, 0x04, 0x41, (byte) 0xE0, (byte) 0xA0, (byte) 0x80 }, prefix);
    }
}