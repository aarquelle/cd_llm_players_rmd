import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(3); // force enlarge path for 4 bytes
        v.putInt(0x01020304);

        assertArrayEquals(new byte[] { 1, 2, 3, 4 }, java.util.Arrays.copyOf(v.data, 4));
        assertEquals(4, v.length);
    }
}