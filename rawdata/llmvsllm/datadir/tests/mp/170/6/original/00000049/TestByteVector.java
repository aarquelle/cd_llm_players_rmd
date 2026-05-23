import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(4);
// needs 5 bytes total (2 + 3)
v.putUTF8("\u20AC");
assertTrue(data(v).length >= 5);
assertArrayEquals(new byte[] { 0, 3, (byte) 0xE2, (byte) 0x82, (byte) 0xAC }, Arrays.copyOf(data(v), length(v)));
    }
}