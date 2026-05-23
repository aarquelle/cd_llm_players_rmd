import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(10);
v.putByte(9);
v.putByteArray(null, 0, 3);
assertEquals(4, v.length);
assertArrayEquals(new byte[] { 9, 0, 0, 0 }, new byte[] { v.data[0], v.data[1], v.data[2], v.data[3] });
    }
}