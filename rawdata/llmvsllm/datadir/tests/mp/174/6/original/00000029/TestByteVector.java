import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(10);
// U+0800 => E0 A0 80
bv.putUTF8("\u0800");
assertEquals(5, bv.length);
assertArrayEquals(new byte[] { 0x00, 0x03, (byte) 0xE0, (byte) 0xA0, (byte) 0x80 }, Arrays.copyOf(bv.data, bv.length));
    }
}