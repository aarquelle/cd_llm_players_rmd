import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(2);
// needs 3 bytes total
bv.putUTF8("A");
assertTrue(bv.data.length >= 3);
assertEquals(3, bv.length);
    }
}