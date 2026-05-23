import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(8);
        bv.putLong(0x1122334455667788L).putLong(0x99AABBCCDDEEFF00L);

        assertEquals(16, bv.length);

        long v1 = ((long) (bv.data[0] & 0xFF) << 56)
                | ((long) (bv.data[1] & 0xFF) << 48)
                | ((long) (bv.data[2] & 0xFF) << 40)
                | ((long) (bv.data[3] & 0xFF) << 32)
                | ((long) (bv.data[4] & 0xFF) << 24)
                | ((long) (bv.data[5] & 0xFF) << 16)
                | ((long) (bv.data[6] & 0xFF) << 8)
                | ((long) (bv.data[7] & 0xFF));

        long v2 = ((long) (bv.data[8] & 0xFF) << 56)
                | ((long) (bv.data[9] & 0xFF) << 48)
                | ((long) (bv.data[10] & 0xFF) << 40)
                | ((long) (bv.data[11] & 0xFF) << 32)
                | ((long) (bv.data[12] & 0xFF) << 24)
                | ((long) (bv.data[13] & 0xFF) << 16)
                | ((long) (bv.data[14] & 0xFF) << 8)
                | ((long) (bv.data[15] & 0xFF));

        assertEquals(0x1122334455667788L ^ 0x99AABBCCDDEEFF00L, v1 ^ v2);
    }
}