import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putUTF8("A\u0800B");

        Field dataF = ByteVector.class.getDeclaredField("data");
        dataF.setAccessible(true);
        Field lenF = ByteVector.class.getDeclaredField("length");
        lenF.setAccessible(true);

        byte[] data = (byte[]) dataF.get(bv);
        int length = (Integer) lenF.get(bv);

        int computed =
                ((data[0] & 0xFF) << 8) | (data[1] & 0xFF);

        int signature =
                ((data[2] & 0xFF) << 24) |
                ((data[3] & 0xFF) << 16) |
                ((data[4] & 0xFF) << 8) |
                (data[6] & 0xFF);

        assertEquals(5, computed);
        assertEquals((0x41 << 24) | (0xE0 << 16) | (0xA0 << 8) | 0x42, signature);
    }
}