import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(16);
// '¢' -> C2 A2
bv.putUTF8("A\u00A2");
assertEquals(5, bv.length);
assertArrayEquals(new byte[] { 0, 3, 'A', (byte) 0xC2, (byte) 0xA2 }, Arrays.copyOf(bv.data, bv.length));
    }
}