import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                String s = "A\u0000\u07FF\u0800";
        ByteVector bv = new ByteVector(2);
        bv.putUTF8(s);

        assertEquals(8, ((bv.data[0] & 0xFF) << 8) | (bv.data[1] & 0xFF));
        assertArrayEquals(new byte[] { 65, -64, -128, -33, -65, -32, -96, -128 }, new byte[] {
                bv.data[2], bv.data[3], bv.data[4], bv.data[5], bv.data[6], bv.data[7], bv.data[8], bv.data[9]
        });
    }
}