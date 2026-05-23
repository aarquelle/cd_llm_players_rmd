import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(4);
bv.putByteArray(null, 0, 3);
assertEquals(3, bv.length);
assertArrayEquals(new byte[] { 0, 0, 0 }, Arrays.copyOf(bv.data, bv.length));
    }
}