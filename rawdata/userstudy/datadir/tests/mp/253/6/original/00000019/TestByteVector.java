import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(4);
byte[] src = new byte[] { 9, 8, 7, 6, 5 };
bv.putByteArray(src, 1, 3);
assertEquals(3, bv.length);
assertArrayEquals(new byte[] { 8, 7, 6 }, new byte[] { bv.data[0], bv.data[1], bv.data[2] });
    }
}