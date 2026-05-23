import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(10);
bv.putByte(0x7F);
bv.putByteArray(null, 0, 3);
assertEquals(4, bv.length);
assertEquals(0, bv.data[1] & 0xFF);
    }
}