import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(0);
        byte[] src = new byte[] { 9, 8, 7, 6, 5 };

        v.putByteArray(src, 1, 3);

        assertArrayEquals(new byte[] { 8, 7, 6 }, java.util.Arrays.copyOf(v.data, v.length));
        assertEquals(3, v.length);
    }
}