import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);
        bv.putByte(0x11).putInt(0x89ABCDEF);

        assertArrayEquals(new byte[] { 0x11, (byte) 0x89, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF },
                java.util.Arrays.copyOf(bv.data, bv.length));
        assertEquals(5, bv.length);
    }
}