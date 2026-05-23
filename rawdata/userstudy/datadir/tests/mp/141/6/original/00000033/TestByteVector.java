import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
            ByteVector bv = new ByteVector(0);
    bv.put11(1, 2);
    assertEquals(2, bv.length);
    assertEquals((byte) 1, bv.data[0]);
    }
}