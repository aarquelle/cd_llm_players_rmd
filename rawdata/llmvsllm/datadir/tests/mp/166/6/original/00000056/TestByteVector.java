import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putByte(0x7E);
        bv.put12(0xAB, 0xCDEF);

        assertArrayEquals(new byte[] { 0x7E, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF }, bv.data);
        assertEquals(4, bv.length);
    }
}