import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(10);
byte[] src = new byte[] { 1, 2, 3, 4, 5 };
bv.putByteArray(src, 1, 3);
assertEquals((byte) 2, bv.data[0]);
assertEquals((byte) 4, bv.data[2]);
    }
}