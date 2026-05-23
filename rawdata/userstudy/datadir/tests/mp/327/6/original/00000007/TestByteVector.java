import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(2);
v.putShort(0x1234);
assertArrayEquals(new byte[] { 0x12, 0x34 }, new byte[] { v.data[0], v.data[1] });
assertEquals(2, v.length);
    }
}