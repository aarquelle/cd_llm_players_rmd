import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(3);
bv.put12(0xAA, 0x1234);
assertEquals((byte) 0x34, bv.data[2]);
assertEquals(3, bv.length);
    }
}