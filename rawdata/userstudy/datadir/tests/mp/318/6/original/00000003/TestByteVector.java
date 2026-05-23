import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
            ByteVector bv = new ByteVector(2);
    bv.put11(10, 20);
    assertEquals(2, bv.length);
    assertEquals((byte) 10, bv.data[0]);
    }
}