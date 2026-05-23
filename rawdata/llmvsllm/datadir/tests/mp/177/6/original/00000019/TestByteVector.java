import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(2);
int oldCap = v.data.length;
v.put12(1, 0x0203);
assertTrue(v.data.length > oldCap);
assertArrayEquals(new byte[] { 1, 2, 3 }, Arrays.copyOf(v.data, v.length));
    }
}