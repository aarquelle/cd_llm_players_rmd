import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(4);
bv.putInt(0x11223344);
assertEquals(4, bv.length);
assertArrayEquals(new byte[] { 0x11, 0x22, 0x33, 0x44 }, new byte[] { bv.data[0], bv.data[1], bv.data[2], bv.data[3] });
    }
}