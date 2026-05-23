import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(10);
// U+0800 -> 3-byte
bv.putUTF8("\u0800");
assertEquals(5, bv.length);
assertEquals((byte) 0x80, bv.data[4]);
    }
}