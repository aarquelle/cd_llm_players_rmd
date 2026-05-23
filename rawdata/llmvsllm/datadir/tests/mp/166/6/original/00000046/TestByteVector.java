import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3);
        bv.put12(0xA5, 0x0102);

        assertArrayEquals(new byte[] { (byte) 0xA5, (byte) 0x01, (byte) 0x02 }, bv.data);
        assertEquals(3, bv.length);
    }
}