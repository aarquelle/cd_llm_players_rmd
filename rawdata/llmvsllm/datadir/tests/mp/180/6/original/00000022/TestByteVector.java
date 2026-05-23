import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(10);
ByteVector returned = bv.putByte(1).putShort(2);
assertSame(bv, returned);
assertEquals(3, bv.length);
    }
}