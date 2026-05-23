import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(1);
bv.putByte(0x11);
bv.putByteArray(new byte[] { 0x22, 0x33 }, 0, 2);
assertEquals(3, bv.length);
assertArrayEquals(new byte[] { 0x11, 0x22, 0x33 }, new byte[] { bv.data[0], bv.data[1], bv.data[2] });
    }
}