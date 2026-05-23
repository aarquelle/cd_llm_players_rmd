import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(3);
v.put12(0x7F, 0x1234);
assertArrayEquals(new byte[] { 0x7F, 0x12, 0x34 }, new byte[] { v.data[0], v.data[1], v.data[2] });
assertEquals(3, v.length);
    }
}