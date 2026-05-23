import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(4);
        bv.putUTF8("\u0080"); // requires 2-byte UTF8 encoding

        java.lang.reflect.Field dataF = ByteVector.class.getDeclaredField("data");
        java.lang.reflect.Field lenF = ByteVector.class.getDeclaredField("length");
        dataF.setAccessible(true);
        lenF.setAccessible(true);

        byte[] data = (byte[]) dataF.get(bv);
        int len = (Integer) lenF.get(bv);

        assertEquals(4, len);
        assertArrayEquals(new byte[] {0, 2, (byte) 0xC2, (byte) 0x80}, new byte[] {data[0], data[1], data[2], data[3]});
    }
}