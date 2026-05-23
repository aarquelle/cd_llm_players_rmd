import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putByte(1).putByte(2);
        bv.putByteArray(new byte[] { 3, 4, 5 }, 0, 3);

        assertTrue(bv.data.length >= 5);
        assertArrayEquals(new byte[] { 1, 2, 3, 4, 5 }, new byte[] { bv.data[0], bv.data[1], bv.data[2], bv.data[3], bv.data[4] });
    }
}