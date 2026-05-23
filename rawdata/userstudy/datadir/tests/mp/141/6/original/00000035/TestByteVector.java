import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
            ByteVector bv = new ByteVector(0);
    bv.putInt(0xAABBCCDD);
    assertEquals(4, bv.length);
    assertEquals((byte) 0xAA, bv.data[0]);
    }
}