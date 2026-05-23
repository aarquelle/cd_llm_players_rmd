import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3);
        bv.putByte(0x7F); // sentinel
        bv.putUTF8("A\u0800"); // 'A' (1 byte) + U+0800 (3 bytes) => 4 bytes payload

        java.lang.reflect.Field dataF = ByteVector.class.getDeclaredField("data");
        dataF.setAccessible(true);
        byte[] data = (byte[]) dataF.get(bv);

        java.lang.reflect.Field lenF = ByteVector.class.getDeclaredField("length");
        lenF.setAccessible(true);
        int len = (Integer) lenF.get(bv);

        assertEquals(1 + 2 + 4, len);

        int headerIndex = 1;
        int h = ((data[headerIndex] & 0xFF) << 8) | (data[headerIndex + 1] & 0xFF);
        assertEquals(4, h);
    }
}