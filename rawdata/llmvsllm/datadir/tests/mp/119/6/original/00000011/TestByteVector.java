import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(5);
v.putUTF8("A");
assertEquals(3, v.length);
assertArrayEquals(new byte[] { 0x00, 0x01, 0x41 }, new byte[] { v.data[0], v.data[1], v.data[2] });
    }
}