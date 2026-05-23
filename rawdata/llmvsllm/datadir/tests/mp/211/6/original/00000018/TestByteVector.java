import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(10);
// bytes: 'A'(1) + '¢'(2) + 'B'(1) => 4
v.putUTF8("A\u00A2B");
assertEquals(6, v.length);
assertArrayEquals(new byte[] { 0, 4, 'A', (byte) 0xC2, (byte) 0xA2, 'B' }, new byte[] { v.data[0], v.data[1], v.data[2], v.data[3], v.data[4], v.data[5] });
    }
}