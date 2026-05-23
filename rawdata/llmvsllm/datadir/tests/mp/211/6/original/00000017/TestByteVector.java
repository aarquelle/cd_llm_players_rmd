import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(10);
// EURO SIGN: 0xE2 0x82 0xAC
v.putUTF8("\u20AC");
assertEquals(5, v.length);
assertArrayEquals(new byte[] { 0, 3, (byte) 0xE2, (byte) 0x82, (byte) 0xAC }, new byte[] { v.data[0], v.data[1], v.data[2], v.data[3], v.data[4] });
    }
}