import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(10);
// bytes: 1 + 2 + 1 = 4
bv.putUTF8("A\u00A2B");
assertEquals(6, bv.length);
assertArrayEquals(new byte[] { 0x00, 0x04 }, new byte[] { bv.data[0], bv.data[1] });
    }
}