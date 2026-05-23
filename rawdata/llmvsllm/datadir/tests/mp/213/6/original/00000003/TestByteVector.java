import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(1);
ByteVector r = v.putByte(0xAB);
assertSame(v, r);
assertEquals((byte) 0xAB, v.data[0]);
    }
}