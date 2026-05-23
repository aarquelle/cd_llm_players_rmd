import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(5);
bv.putUTF8("AB");
assertEquals(4, bv.length);
assertArrayEquals(new byte[] { 0x00, 0x02, 0x41, 0x42 }, new byte[] { bv.data[0], bv.data[1], bv.data[2], bv.data[3] });
    }
}