import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(10);
v.putUTF8("A\u0800");
assertEquals(6, v.length);
assertArrayEquals(new byte[] { 0x00, 0x04 }, new byte[] { v.data[0], v.data[1] });
    }
}