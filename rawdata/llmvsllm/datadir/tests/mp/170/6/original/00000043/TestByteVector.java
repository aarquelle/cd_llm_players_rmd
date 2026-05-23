import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(16);
// CENT SIGN, 2-byte UTF-8
v.putUTF8("\u00A2");
assertEquals(4, length(v));
assertArrayEquals(new byte[] { 0, 2, (byte) 0xC2, (byte) 0xA2 }, Arrays.copyOf(data(v), length(v)));
    }
}