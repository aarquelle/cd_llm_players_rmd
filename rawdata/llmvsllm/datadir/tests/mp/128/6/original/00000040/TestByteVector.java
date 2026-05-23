import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.putByte(0x7F);

        byte[] src = new byte[] { 9, 8, 7, 6, 5 };
        v.putByteArray(src, 2, 3); // should append {7,6,5}

        assertArrayEquals(new byte[] { 0x7F, 7, 6, 5 }, java.util.Arrays.copyOf(v.data, v.length));
        assertEquals(4, v.length);
    }
}