import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(10);
ByteVector r = v.putByte(1).putShort(2).putInt(3);
assertSame(v, r);
assertEquals(1 + 2 + 4, v.length);
    }
}