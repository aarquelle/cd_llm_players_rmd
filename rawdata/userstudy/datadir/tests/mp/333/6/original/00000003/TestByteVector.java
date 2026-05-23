import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(1);
bv.putByte(0x7F);
assertEquals(1, bv.length);
assertEquals((byte) 0x7F, bv.data[0]);
    }
}