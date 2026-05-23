import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(5);
bv.putByte(0x55);
bv.putByteArray(null, 0, 2);
assertEquals(3, bv.length);
assertEquals((byte) 0x55, bv.data[0]);
    }
}