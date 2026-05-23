import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(3);
bv.put12(0x7F, 0x0203);
assertEquals(3, bv.length);
assertArrayEquals(new byte[] { 0x7F, 0x02, 0x03 }, Arrays.copyOf(bv.data, bv.length));
    }
}