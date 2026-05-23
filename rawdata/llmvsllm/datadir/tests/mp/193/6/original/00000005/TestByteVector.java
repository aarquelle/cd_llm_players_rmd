import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(2);
bv.put11(0x11, 0x22);
assertEquals(2, bv.length);
assertArrayEquals(new byte[] { 0x11, 0x22 }, new byte[] { bv.data[0], bv.data[1] });
    }
}