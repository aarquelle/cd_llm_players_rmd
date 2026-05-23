import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(10);
// Euro sign U+20AC => E2 82 AC, length 3
v.putUTF8("\u20AC");
assertEquals((byte) 0x00, v.data[0]);
assertEquals((byte) 0x03, v.data[1]);
    }
}