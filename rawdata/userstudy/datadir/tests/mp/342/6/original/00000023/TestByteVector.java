import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putByte(0x11).putByte(0x22).putByte(0x33);

        assertEquals(4, bv.data.length);
        assertArrayEquals(new byte[] { 0x11, 0x22, 0x33 }, new byte[] { bv.data[0], bv.data[1], bv.data[2] });
    }
}