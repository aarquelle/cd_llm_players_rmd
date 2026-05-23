import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(10);
// optimistic length would be 2 but actual is 4 bytes
bv.putUTF8("A\u20AC");
assertEquals(6, bv.length);
assertArrayEquals(new byte[] { 0x00, 0x04, 0x41, (byte) 0xE2, (byte) 0x82, (byte) 0xAC }, new byte[] { bv.data[0], bv.data[1], bv.data[2], bv.data[3], bv.data[4], bv.data[5] });
    }
}