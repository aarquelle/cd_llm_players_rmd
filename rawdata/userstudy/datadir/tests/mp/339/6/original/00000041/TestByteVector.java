import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1); // force enlarge path
        v.putInt(0x01020304);

        assertEquals(4, v.length);
        assertArrayEquals(new byte[] { 1, 2, 3, 4 }, java.util.Arrays.copyOf(v.data, 4));
    }
}