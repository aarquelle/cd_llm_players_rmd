import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);
        bv.put11(0xA5, 0x7F);

        assertArrayEquals(new byte[] { (byte) 0xA5, (byte) 0x7F }, java.util.Arrays.copyOf(bv.data, bv.length));
        assertEquals(2, bv.length);
    }
}