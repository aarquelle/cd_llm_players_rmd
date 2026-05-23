import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);
        bv.putByte(0x7A).putByte(0x7B); // triggers enlarge(1) when adding second byte

        assertEquals(2, bv.data.length);
        assertArrayEquals(new byte[] { (byte) 0x7A, (byte) 0x7B }, new byte[] { bv.data[0], bv.data[1] });
    }
}