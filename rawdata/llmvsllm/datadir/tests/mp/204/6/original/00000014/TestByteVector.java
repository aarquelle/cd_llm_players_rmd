import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        // "é" requires 2+2=4, ok; add one more byte to force enlarge
ByteVector v = new ByteVector(4);
v.putByte(0x11);
// needs 4 bytes, total 5 -> enlarge
v.putUTF8("é");
assertEquals(5, v.length);
assertTrue(v.data.length >= 5);
    }
}