import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1); // force enlarge path
        bv.length = 1;
        bv.data[0] = 0x55;

        bv.putShort(0xA1B2);

        assertArrayEquals(new byte[] { 0x55, (byte) 0xA1, (byte) 0xB2 }, bv.data);
        assertEquals(3, bv.length);
    }
}