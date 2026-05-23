import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(1);
v.putByte(7);
int oldCap = v.data.length;
v.putByte(8);
assertTrue(v.data.length > oldCap);
assertArrayEquals(new byte[] { 7, 8 }, Arrays.copyOf(v.data, v.length));
    }
}