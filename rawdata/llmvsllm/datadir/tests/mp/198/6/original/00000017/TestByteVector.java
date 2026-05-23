import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(10);
// EURO SIGN, 3-byte UTF-8
bv.putUTF8("\u20AC");
assertEquals(5, bv.length);
// byte length = 3
assertEquals((byte) 0x03, bv.data[1]);
    }
}