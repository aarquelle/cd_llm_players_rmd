import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(2);
bv.put11(0xAA, 0xBB);
assertEquals(0xAA, bv.data[0] & 0xFF);
assertEquals(0xBB, bv.data[1] & 0xFF);
    }
}