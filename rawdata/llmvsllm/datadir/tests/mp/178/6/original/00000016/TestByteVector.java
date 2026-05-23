import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(10);
// ¢ => C2 A2
v.putUTF8("\u00A2");
assertEquals(4, v.length);
assertArrayEquals(new byte[] { 0x00, 0x02, (byte) 0xC2, (byte) 0xA2 }, new byte[] { v.data[0], v.data[1], v.data[2], v.data[3] });
    }
}