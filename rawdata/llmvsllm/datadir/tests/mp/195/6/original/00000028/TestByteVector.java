import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(10);
byte[] src = new byte[] { 10, 20, 30, 40 };
bv.putByteArray(src, 1, 2);
assertEquals(20, bv.data[0] & 0xFF);
assertEquals(30, bv.data[1] & 0xFF);
    }
}