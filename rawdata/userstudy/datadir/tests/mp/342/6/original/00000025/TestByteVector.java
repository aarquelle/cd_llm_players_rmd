import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);

        byte[] src = new byte[] { 7, 8, 9, 10 };
        v.putByte(0x55);                // existing byte ensures correct dest offset
        v.putByteArray(src, 1, 2);      // copies 8,9; forces enlarge from capacity 1

        assertEquals(3, v.length);
        assertArrayEquals(new byte[] { (byte) 0x55, 8, 9 }, new byte[] { v.data[0], v.data[1], v.data[2] });
    }
}