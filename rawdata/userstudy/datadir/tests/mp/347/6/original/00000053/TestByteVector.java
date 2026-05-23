import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.putByte(0x7A);
        byte[] src = new byte[] { 9, 8, 7, 6, 5 };
        v.putByteArray(src, 1, 3); // copies 8,7,6 and must enlarge

        assertEquals(4, v.length);
        assertArrayEquals(new byte[] { 0x7A, 8, 7, 6 }, Arrays.copyOf(v.data, v.length));
    }
}