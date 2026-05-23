import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);

        bv.putUTF8("a\u0080\u0800z");

        assertEquals(9, bv.length);
        assertEquals(0x00, bv.data[0]);
    }
}