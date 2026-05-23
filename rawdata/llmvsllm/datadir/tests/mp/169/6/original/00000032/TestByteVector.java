import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(4);
        bv.putByte(0x55).putUTF8("\u00A2"); // '¢' => UTF-8: C2 A2, length header: 00 02

        assertEquals(1 + 2 + 2, bv.length);
        assertArrayEquals(new byte[] { (byte) 0x55, 0x00, 0x02, (byte) 0xC2, (byte) 0xA2 }, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}