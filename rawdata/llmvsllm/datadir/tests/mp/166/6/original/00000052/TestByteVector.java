import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putByte(0x7F);
        bv.put12(0x12, 0xABCD);

        assertArrayEquals(new byte[] { (byte) 0x7F, (byte) 0x12, (byte) 0xAB, (byte) 0xCD }, bv.data);
        assertEquals(4, bv.length);
    }
}