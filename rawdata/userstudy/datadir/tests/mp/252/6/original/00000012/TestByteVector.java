import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        // enough for optimistic 2+2=4, not enough for actual 2+4=6
ByteVector v = new ByteVector(5);
v.putUTF8("A\u20AC");
assertTrue(v.data.length >= 6);
assertEquals(6, v.length);
    }
}