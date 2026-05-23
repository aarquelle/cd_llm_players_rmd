import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(0);
        bv.putLong(Long.MAX_VALUE);
        assertEquals(8, bv.length);
        assertArrayEquals(new byte[]{127, -1, 127, 127, 127, 127, 127, 127}, bv.data);
    }
}