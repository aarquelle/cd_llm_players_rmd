import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(10);
byte[] src = new byte[] { 10, 11, 12, 13, 14 };
v.putByteArray(src, 1, 3);
assertEquals(3, v.length);
assertArrayEquals(new byte[] { 11, 12, 13 }, new byte[] { v.data[0], v.data[1], v.data[2] });
    }
}