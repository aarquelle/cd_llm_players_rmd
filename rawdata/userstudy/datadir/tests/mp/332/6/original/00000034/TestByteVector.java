import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);

        byte[] src = new byte[] { 9, 8, 7, 6, 5 };
        v.putByte(1);
        v.putByteArray(src, 1, 3);      // copies 8,7,6
        v.putByteArray(null, 0, 2);     // appends two zeros

        assertArrayEquals(new byte[] { 1, 8, 7, 6, 0, 0 }, java.util.Arrays.copyOf(v.data, v.length));
        assertEquals(6, v.length);
    }
}