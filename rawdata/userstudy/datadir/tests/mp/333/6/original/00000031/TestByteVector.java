import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);

        bv.putByteArray(new byte[] { 9, 8, 7, 6, 5 }, 1, 3) // expect 8,7,6
          .putByteArray(null, 0, 2); // append two zero bytes

        assertArrayEquals(new byte[] { 8, 7, 6, 0, 0 }, java.util.Arrays.copyOf(bv.data, bv.length));
        assertEquals(5, bv.length);
    }
}