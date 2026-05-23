import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(10);
bv.putByteArray(new byte[] { 1, 2, 3, 4 }, 1, 2);
assertArrayEquals(new byte[] { 2, 3 }, new byte[] { bv.data[0], bv.data[1] });
assertEquals(2, bv.length);
    }
}