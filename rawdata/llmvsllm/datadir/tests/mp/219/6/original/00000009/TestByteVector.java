import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(3);
bv.put12(0x7F, 0x0102);
assertArrayEquals(new byte[] { 0x7F, 0x01, 0x02 }, new byte[] { bv.data[0], bv.data[1], bv.data[2] });
assertEquals(3, bv.length);
    }
}