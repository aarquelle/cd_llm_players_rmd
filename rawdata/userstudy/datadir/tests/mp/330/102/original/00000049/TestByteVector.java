import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(0);
        bv.putLong(12345678);
        assertEquals(8, bv.length);
        assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 5, 6, 7, 8}, bv.data);
    }
}