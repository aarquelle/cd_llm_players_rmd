import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(10);
        bv.put12(0x7A, 0x1234);

        assertEquals(3, bv.length);
        assertArrayEquals(new byte[] { 0x7A, 0x12, 0x34 }, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}