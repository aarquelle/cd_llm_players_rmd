import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(1);
bv.putByte(1).putByte(2);
assertTrue(bv.data.length >= 2);
assertEquals((byte) 1, bv.data[0]);
    }
}