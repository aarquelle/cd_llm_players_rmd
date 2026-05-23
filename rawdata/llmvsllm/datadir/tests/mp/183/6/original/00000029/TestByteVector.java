import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        // forces enlarge: needs 2 + 2 = 4 bytes for "\u00A2"
ByteVector bv = new ByteVector(3);
bv.putUTF8("\u00A2");
assertTrue(bv.data.length >= 4);
assertArrayEquals(new byte[] { 0x00, 0x02, (byte) 0xC2, (byte) 0xA2 }, Arrays.copyOf(bv.data, bv.length));
    }
}