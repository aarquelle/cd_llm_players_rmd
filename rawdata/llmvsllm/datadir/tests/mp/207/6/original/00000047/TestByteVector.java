import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.length = 1;
        bv.data[0] = (byte) 0x7F;

        ByteVector ret = bv.put11(0xAB, 0xCD);

        assertEquals(bv, ret);
        assertArrayEquals(new byte[] { (byte) 0x7F, (byte) 0xAB, (byte) 0xCD }, new byte[] { bv.data[0], bv.data[1], bv.data[2] });
    }
}