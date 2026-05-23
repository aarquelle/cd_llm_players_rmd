import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(2);
bv.putByte(0x7F);
bv.putByteArray(new byte[] { 1, 2, 3 }, 0, 3);
assertTrue(bv.data.length >= 4);
assertEquals((byte) 0x7F, bv.data[0]);
    }
}