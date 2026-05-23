import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(1);
ByteVector returned = bv.putByte(0xAB);
assertSame(bv, returned);
assertArrayEquals(new byte[] { (byte) 0xAB }, Arrays.copyOf(bv.data, bv.length));
    }
}