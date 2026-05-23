import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(10);
byte[] src = new byte[] { 9, 8, 7, 6 };
bv.putByte(1);
// copies 8,7 to indices 1,2
bv.putByteArray(src, 1, 2);
assertEquals((byte) 8, bv.data[1]);
assertEquals((byte) 7, bv.data[2]);
    }
}