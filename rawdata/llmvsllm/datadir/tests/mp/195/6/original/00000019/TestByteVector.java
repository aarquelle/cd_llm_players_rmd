import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(10);
        bv.putUTF8("ABC");

        assertArrayEquals(new byte[] { 'A', 'B', 'C' }, new byte[] { bv.data[2], bv.data[3], bv.data[4] });
        assertEquals(5, bv.length);
    }
}