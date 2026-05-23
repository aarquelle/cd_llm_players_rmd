import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(5);
v.putUTF8("ABC");
assertArrayEquals(new byte[] { 0x00, 0x03, 0x41, 0x42, 0x43 }, new byte[] { v.data[0], v.data[1], v.data[2], v.data[3], v.data[4] });
assertEquals(5, v.length);
    }
}