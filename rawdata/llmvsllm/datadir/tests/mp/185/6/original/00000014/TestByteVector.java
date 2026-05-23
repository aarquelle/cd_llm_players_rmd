import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(8);
bv.putLong(0x0102030405060708L);
assertEquals(8, bv.length);
assertArrayEquals(new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }, new byte[] { bv.data[0], bv.data[1], bv.data[2], bv.data[3], bv.data[4], bv.data[5], bv.data[6], bv.data[7] });
    }
}