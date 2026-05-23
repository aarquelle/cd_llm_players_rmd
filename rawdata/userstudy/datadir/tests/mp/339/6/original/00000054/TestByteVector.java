import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2); // force enlarge on put12
        v.put12(0xAB, 0x1234);

        assertArrayEquals(new byte[] { (byte) 0xAB, 0x12, 0x34 }, java.util.Arrays.copyOf(v.data, v.length));
        assertEquals(3, v.length);
    }
}