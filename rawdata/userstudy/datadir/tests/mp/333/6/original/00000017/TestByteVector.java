import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(10);
// U+0905 -> E0 A4 85
bv.putUTF8("अ");
assertEquals(5, bv.length);
assertArrayEquals(new byte[] { 0x00, 0x03, (byte) 0xE0, (byte) 0xA4, (byte) 0x85 }, new byte[] { bv.data[0], bv.data[1], bv.data[2], bv.data[3], bv.data[4] });
    }
}