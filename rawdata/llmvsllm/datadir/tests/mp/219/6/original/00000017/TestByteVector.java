import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(10);
bv.putUTF8("\u0800");
assertArrayEquals(new byte[] { 0x00, 0x03, (byte) 0xE0, (byte) 0xA0, (byte) 0x80 }, new byte[] { bv.data[0], bv.data[1], bv.data[2], bv.data[3], bv.data[4] });
assertEquals(5, bv.length);
    }
}