import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(10);
byte[] src = new byte[] { 1, 2, 3, 4 };
bv.putByteArray(src, 0, 4);
assertEquals(4, bv.length);
assertEquals(4, bv.data[3] & 0xFF);
    }
}