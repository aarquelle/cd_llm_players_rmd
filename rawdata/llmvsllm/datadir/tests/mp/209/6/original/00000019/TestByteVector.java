import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(10);
// é -> C3 A9
v.putUTF8("\u00E9");
assertEquals(0xC3, v.data[2] & 0xFF);
assertEquals(4, v.length);
    }
}