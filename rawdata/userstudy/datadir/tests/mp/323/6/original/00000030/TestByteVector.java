import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putByte(0x55);

        byte[] src = new byte[] { 9, 10, 11, 12, 13 };

        bv.putByteArray(src, 2, 3); // should copy 11,12,13

        assertArrayEquals(new byte[] { (byte) 0x55, 11, 12, 13 }, bv.data);
        assertEquals(4, bv.length);
    }
}