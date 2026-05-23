import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.length = 1;
        bv.data[0] = 0x55;

        ByteVector ret = bv.put12(0xAB, 0xCDEF);

        assertSame(bv, ret);
        assertArrayEquals(new byte[] { 0x55, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF }, Arrays.copyOf(bv.data, bv.length));
    }
}