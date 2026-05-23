import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(4);
// needs 2+3=5 bytes
v.putUTF8("\u20AC");
assertEquals(5, v.length);
assertTrue(v.data.length >= 5);
    }
}