import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(1);
bv.putByte(1);
bv.putByteArray(new byte[] { 2, 3, 4 }, 0, 3);
assertTrue(bv.data.length >= 4);
assertArrayEquals(new byte[] { 1, 2, 3, 4 }, Arrays.copyOf(bv.data, bv.length));
    }
}