import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(5);
byte[] src = new byte[] { 10, 11, 12, 13 };
v.putByteArray(src, 1, 2);
assertEquals(2, v.length);
assertArrayEquals(new byte[] { 11, 12 }, Arrays.copyOf(v.data, v.length));
    }
}