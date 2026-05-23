import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(1);
ByteVector ret = bv.putByte(0xAB);
assertSame(bv, ret);
assertEquals((byte) 0xAB, bv.data[0]);
    }
}