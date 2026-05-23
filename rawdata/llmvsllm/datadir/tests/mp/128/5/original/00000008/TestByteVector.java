import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(4);
        bv.putByteArray(new byte[] {1, 2, 3, 4}, 0, 4);
        assertEquals(4, bv.length);
        assertArrayEquals(new byte[] {1, 2, 3, 4}, bv.data);
    }
}