import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(10);
// U+00E9 => C3 A9
v.putUTF8("é");
assertEquals((byte) 0xC3, v.data[2]);
assertEquals((byte) 0xA9, v.data[3]);
    }
}