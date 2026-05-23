import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(2);
bv.put11(0x12, 0xAB);
assertArrayEquals(new byte[] { 0x12, (byte) 0xAB }, new byte[] { bv.data[0], bv.data[1] });
assertEquals(2, bv.length);
    }
}