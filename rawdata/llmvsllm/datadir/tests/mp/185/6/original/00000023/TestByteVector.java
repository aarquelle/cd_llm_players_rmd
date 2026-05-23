import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(2);
bv.putByte(1);
bv.putByteArray(new byte[] { 2, 3, 4 }, 0, 3);
assertTrue(bv.data.length >= 4);
assertEquals((byte) 1, bv.data[0]);
    }
}