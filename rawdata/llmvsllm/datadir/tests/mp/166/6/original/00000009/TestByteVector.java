import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(3);
bv.putInt(0x01020304);
assertTrue(bv.data.length >= 4);
assertEquals(4, bv.length);
    }
}