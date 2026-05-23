import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);
        bv.putByte(0x7F);
        bv.put11(0x01, 0x02);

        assertTrue(bv.data.length >= 3);
        assertArrayEquals(new byte[] { 0x7F, 0x01, 0x02 }, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}