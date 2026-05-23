import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(3);
v.put12(0x7F, 0x1122);
assertEquals(3, v.length);
assertArrayEquals(new byte[] { 0x7F, 0x11, 0x22 }, Arrays.copyOf(v.data, v.length));
    }
}