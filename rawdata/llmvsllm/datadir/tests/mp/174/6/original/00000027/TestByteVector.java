import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(10);
// U+0080 => C2 80 in modified UTF-8-like encoding used here
bv.putUTF8("\u0080");
assertEquals(4, bv.length);
assertArrayEquals(new byte[] { 0x00, 0x02, (byte) 0xC2, (byte) 0x80 }, Arrays.copyOf(bv.data, bv.length));
    }
}