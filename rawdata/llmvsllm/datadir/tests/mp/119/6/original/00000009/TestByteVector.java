import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(4);
v.putInt(0x01020304);
assertEquals(4, v.length);
assertArrayEquals(new byte[] { 0x01, 0x02, 0x03, 0x04 }, new byte[] { v.data[0], v.data[1], v.data[2], v.data[3] });
    }
}