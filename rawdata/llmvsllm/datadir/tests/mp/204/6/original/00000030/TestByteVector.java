import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putByte(0x7F);
        bv.putShort(0xABCD);

        byte[] data = bv.data;

        assertArrayEquals(new byte[] { (byte) 0x7F, (byte) 0xAB, (byte) 0xCD }, new byte[] { data[0], data[1], data[2] });
        assertEquals(3, bv.length);
    }
}