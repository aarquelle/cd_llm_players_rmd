import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);
        bv.putShort(0x0102);

        assertTrue(bv.data.length >= 2);
        assertArrayEquals(new byte[] { 0x01, 0x02 }, new byte[] { bv.data[0], bv.data[1] });
    }
}