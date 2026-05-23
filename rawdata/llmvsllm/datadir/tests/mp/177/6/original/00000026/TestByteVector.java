import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(8);
        v.putLong(0x0102030405060708L);

        assertEquals(8, v.length);
        assertEquals(0x0102030405060708L, ((long) (v.data[0] & 0xFF) << 56)
                | ((long) (v.data[1] & 0xFF) << 48)
                | ((long) (v.data[2] & 0xFF) << 40)
                | ((long) (v.data[3] & 0xFF) << 32)
                | ((long) (v.data[4] & 0xFF) << 24)
                | ((long) (v.data[5] & 0xFF) << 16)
                | ((long) (v.data[6] & 0xFF) << 8)
                | ((long) (v.data[7] & 0xFF)));
    }
}