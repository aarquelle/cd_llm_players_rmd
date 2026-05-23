import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(1);
ByteVector r = v.putByte(0x7F);
assertSame(v, r);
assertEquals(0x7F, v.data[0] & 0xFF);
    }
}