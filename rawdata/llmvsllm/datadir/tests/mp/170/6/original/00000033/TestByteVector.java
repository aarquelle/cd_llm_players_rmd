import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(8);
v.putLong(0x0102030405060708L);
assertEquals(8, length(v));
assertArrayEquals(new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }, Arrays.copyOf(data(v), length(v)));
    }
}