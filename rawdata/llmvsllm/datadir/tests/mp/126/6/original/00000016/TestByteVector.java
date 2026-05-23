import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(10);
// U+0080 -> 2-byte in modified UTF-8 encoding used here
bv.putUTF8("\u0080");
assertEquals(4, bv.length);
assertEquals((byte) 0x80, bv.data[3]);
    }
}