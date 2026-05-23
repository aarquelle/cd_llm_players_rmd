import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3);
        bv.putByte(0x7F).putUTF8("A\u0080\u0800");

        java.lang.reflect.Field dataF = ByteVector.class.getDeclaredField("data");
        java.lang.reflect.Field lenF = ByteVector.class.getDeclaredField("length");
        dataF.setAccessible(true);
        lenF.setAccessible(true);
        byte[] data = (byte[]) dataF.get(bv);
        int length = (Integer) lenF.get(bv);

        assertEquals(1 + 2 + 6, length);

        int packed =
                ((data[0] & 0xFF) << 24) |
                ((data[1] & 0xFF) << 16) |
                ((data[2] & 0xFF) << 8) |
                (data[3] & 0xFF);

        assertEquals(((0x7F << 24) | (0x00 << 16) | (0x06 << 8) | 0x41), packed);
    }
}