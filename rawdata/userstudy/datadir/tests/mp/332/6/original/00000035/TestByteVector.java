import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(3);
        v.putByte(0x55);

        byte[] src = new byte[] { 10, 20, 30, 40, 50 };
        v.putByteArray(src, 1, 3); // copies 20,30,40

        assertEquals(4, v.length);
        assertArrayEquals(new byte[] { 0x55, 20, 30, 40 }, new byte[] { v.data[0], v.data[1], v.data[2], v.data[3] });
    }
}