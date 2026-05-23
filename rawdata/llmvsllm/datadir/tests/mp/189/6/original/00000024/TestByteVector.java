import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(2);
bv.putByte(1).putByte(2);
// requires length 12; double would be 4
bv.putByteArray(new byte[10], 0, 10);
assertEquals(12, bv.data.length);
assertEquals(12, bv.length);
    }
}