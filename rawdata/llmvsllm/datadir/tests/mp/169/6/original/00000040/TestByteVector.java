import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        v.putUTF8("\u0080");

        java.lang.reflect.Field dataF = ByteVector.class.getDeclaredField("data");
        dataF.setAccessible(true);
        byte[] data = (byte[]) dataF.get(v);

        java.lang.reflect.Field lenF = ByteVector.class.getDeclaredField("length");
        lenF.setAccessible(true);
        int len = (Integer) lenF.get(v);

        assertEquals(4, len);
        assertArrayEquals(new byte[] {0, 2, (byte) 0xC2, (byte) 0x80}, new byte[] {data[0], data[1], data[2], data[3]});
    }
}