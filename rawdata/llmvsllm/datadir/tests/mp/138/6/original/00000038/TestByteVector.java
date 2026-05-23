import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.putByte(0x7F);

        byte[] src = new byte[] { 9, 1, 2, 3, 4, 5 };
        v.putByteArray(src, 2, 3); // copies {2,3,4}

        assertArrayEquals(new byte[] { 0x7F, 2, 3, 4 }, java.util.Arrays.copyOf(v.data, v.length));
        assertEquals(4, v.length);
    }
}