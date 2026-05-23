import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(10);
// U+00E9 -> C3 A9
bv.putUTF8("é");
assertEquals(4, bv.length);
assertArrayEquals(new byte[] { 0x00, 0x02, (byte) 0xC3, (byte) 0xA9 }, new byte[] { bv.data[0], bv.data[1], bv.data[2], bv.data[3] });
    }
}