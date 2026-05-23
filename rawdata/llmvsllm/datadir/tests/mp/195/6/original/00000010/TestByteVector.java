import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(3);
bv.put12(0xAB, 0xCDEF);
assertEquals(0xAB, bv.data[0] & 0xFF);
assertEquals(0xCD, bv.data[1] & 0xFF);
    }
}