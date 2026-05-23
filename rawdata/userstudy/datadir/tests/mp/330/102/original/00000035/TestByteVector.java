import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(0);
        bv.putUTF8("\177");
        assertEquals(3, bv.length);
        assertArrayEquals(new byte[]{1, 7, 7}, bv.data);
    }
}