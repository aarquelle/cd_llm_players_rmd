import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(1);
byte[] src = new byte[] { 1, 2, 3, 4 };
ByteVector r = v.putByteArray(src, 0, 4);
assertSame(v, r);
assertEquals(4, v.length);
    }
}