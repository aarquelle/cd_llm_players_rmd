import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(10);
// U+00A2 => 2-byte UTF-8
v.putUTF8("\u00A2");
assertEquals(4, v.length);
assertEquals(2, ((v.data[0] & 0xFF) << 8) | (v.data[1] & 0xFF));
    }
}