import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(1);
bv.putByte(0x5A);
bv.putByteArray(new byte[] { 1, 2 }, 0, 2);
assertEquals((byte) 0x5A, bv.data[0]);
assertEquals(3, bv.length);
    }
}