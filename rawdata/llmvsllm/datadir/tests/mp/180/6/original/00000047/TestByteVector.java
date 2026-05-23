import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.put11(0x01, 0x02).put11(0xA5, 0x5A);

        assertArrayEquals(new byte[] { 0x01, 0x02, (byte) 0xA5, 0x5A },
                java.util.Arrays.copyOf(bv.data, bv.length));
        assertEquals(4, bv.length);
    }
}