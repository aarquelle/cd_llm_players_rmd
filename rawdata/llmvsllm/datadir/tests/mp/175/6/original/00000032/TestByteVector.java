import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(16);
        bv.putUTF8("A\u00A2");

        assertEquals(5, bv.length);
        assertEquals((byte) 0xA2, bv.data[4]);
    }
}