import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3); // force resize when writing 4 bytes total
        bv.putUTF8("\u0080"); // 1 char -> 2-byte UTF8 encoding: C2 80, with 2-byte length prefix 00 02

        assertEquals(4, bv.length);
        assertArrayEquals(new byte[] { 0x00, 0x02, (byte) 0xC2, (byte) 0x80 }, new byte[] { bv.data[0], bv.data[1], bv.data[2], bv.data[3] });
    }
}