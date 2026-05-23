import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(20);
// bytes: 1 + 2 + 3 = 6
v.putUTF8("A\u00A2\u20AC");
assertEquals(8, v.length);
assertEquals(6, ((v.data[0] & 0xFF) << 8) | (v.data[1] & 0xFF));
    }
}