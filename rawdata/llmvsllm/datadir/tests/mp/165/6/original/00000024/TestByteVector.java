import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(2);
bv.putByte(1).putByte(2);
byte[] src = new byte[] { 3, 4, 5 };
bv.putByteArray(src, 0, 3);
assertEquals(5, bv.length);
assertEquals((byte) 2, bv.data[1]);
    }
}