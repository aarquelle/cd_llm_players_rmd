import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(4);
        v.putByteArray(new byte[] {1, 2, 3, 4}, 0, 4);
        v.putByte(5); // triggers enlarge(1)

        assertEquals(8, v.data.length);
        assertArrayEquals(new byte[] {1, 2, 3, 4, 5}, java.util.Arrays.copyOf(v.data, v.length));
    }
}