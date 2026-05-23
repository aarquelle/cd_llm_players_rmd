import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(2);
bv.put12(0x00, 0xABCD);
assertEquals((byte) 0xCD, bv.data[2]);
assertEquals(3, bv.length);
    }
}