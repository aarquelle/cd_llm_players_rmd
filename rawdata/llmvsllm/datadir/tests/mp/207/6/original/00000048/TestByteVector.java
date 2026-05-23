import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        v.length = 1;
        v.data[0] = (byte) 0x55;

        v.put11(0xA5, 0x5A);

        assertEquals(3, v.length);
        assertArrayEquals(new byte[] { (byte) 0x55, (byte) 0xA5, (byte) 0x5A }, new byte[] { v.data[0], v.data[1], v.data[2] });
    }
}