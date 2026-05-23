import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3);
        bv.putInt(0x01020304);

        assertEquals(4, bv.length);
        assertArrayEquals(new byte[] { 1, 2, 3, 4 }, new byte[] { bv.data[0], bv.data[1], bv.data[2], bv.data[3] });
    }
}