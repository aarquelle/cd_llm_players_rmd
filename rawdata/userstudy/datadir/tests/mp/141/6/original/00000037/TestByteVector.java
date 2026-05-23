import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        byte[] b = { 1, 2, 3, 4, 5 };
ByteVector bv = new ByteVector(3);
bv.putByteArray(b, 1, 3);
assertEquals(3, bv.length);
assertArrayEquals(new byte[] { 2, 3, 4 }, java.util.Arrays.copyOfRange(bv.data, 0, 3));
    }
}