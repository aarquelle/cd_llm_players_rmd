import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);
        bv.putByte(0x7F);
        bv.putLong(0x0102030405060708L);

        assertArrayEquals(
                new byte[] { 0x7F, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08 },
                java.util.Arrays.copyOf(bv.data, bv.length)
        );
        assertEquals(9, bv.length);
    }
}