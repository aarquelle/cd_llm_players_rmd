import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(4);
// needs 5 total bytes
bv.putUTF8("\u0800");
assertTrue(bv.data.length >= 5);
assertEquals(5, bv.length);
    }
}