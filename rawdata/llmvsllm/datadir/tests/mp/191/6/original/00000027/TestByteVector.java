import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(10);
bv.putUTF8("A\u0080");
assertArrayEquals(new byte[] { 0x00, 0x03, 0x41, (byte) 0xC2, (byte) 0x80 }, Arrays.copyOf(bv.data, bv.length));
assertEquals(5, bv.length);
    }
}