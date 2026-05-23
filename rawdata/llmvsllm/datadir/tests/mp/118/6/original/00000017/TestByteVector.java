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
assertEquals((byte) 0x02, v.data[1]);
    }
}