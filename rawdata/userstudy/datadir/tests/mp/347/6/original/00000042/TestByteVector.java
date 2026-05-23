import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);

        v.putByteArray(null, 0, 3); // forces enlarge and advances length without copying
        byte[] src = new byte[] { 10, 20, 30, 40, 50 };
        v.putByteArray(src, 1, 3); // copy 20,30,40 after 3 zeros

        assertEquals(6, v.length);
        assertArrayEquals(new byte[] { 0, 0, 0, 20, 30, 40 }, java.util.Arrays.copyOf(v.data, v.length));
    }
}