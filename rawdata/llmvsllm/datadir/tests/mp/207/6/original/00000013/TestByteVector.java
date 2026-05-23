import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(8);
bv.putLong(0x1122334455667788L);
assertEquals(8, bv.length);
assertArrayEquals(new byte[] { 0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77, (byte) 0x88 }, new byte[] { bv.data[0], bv.data[1], bv.data[2], bv.data[3], bv.data[4], bv.data[5], bv.data[6], bv.data[7] });
    }
}