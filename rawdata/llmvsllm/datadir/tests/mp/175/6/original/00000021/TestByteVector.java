import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(1);
bv.putInt(0x0A0B0C0D);
assertEquals(4, bv.length);
assertArrayEquals(new byte[] { 0x0A, 0x0B, 0x0C, 0x0D }, Arrays.copyOf(bv.data, bv.length));
    }
}