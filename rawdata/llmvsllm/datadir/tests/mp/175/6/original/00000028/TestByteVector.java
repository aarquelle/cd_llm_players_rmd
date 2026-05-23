import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(4);

        bv.putLong(0x0000000000000001L);

        assertEquals(8, bv.length);
        assertEquals(1, bv.data[7]);
    }
}