import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(10);
        bv.putUTF8("\u0080");
        assertEquals(4, bv.length);
        assertArrayEquals(new byte[] { 0x00, 0x02, (byte) 0xC2, (byte) 0x80 }, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}