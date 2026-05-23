import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(5);
// charLength=2, actual byteLength=4 => total 6
bv.putUTF8("A\u0800");
assertEquals(6, bv.length);
assertTrue(bv.data.length >= 6);
    }
}