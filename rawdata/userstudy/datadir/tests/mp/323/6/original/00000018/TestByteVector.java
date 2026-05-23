import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(3);
// requires 1 + 2 + 3 = 6 bytes total
bv.putByte(0x11).putUTF8("\u20AC");
assertEquals(6, bv.length);
assertTrue(bv.data.length >= 6);
    }
}