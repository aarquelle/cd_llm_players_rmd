import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);
        byte[] before = bv.data;

        bv.put11(1, 2);

        byte[] after = bv.data;

        assertTrue(after.length > before.length);
        assertEquals(2, bv.length);
    }
}