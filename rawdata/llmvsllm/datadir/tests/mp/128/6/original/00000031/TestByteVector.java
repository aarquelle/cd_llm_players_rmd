import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3);
        bv.putByte(0x7F);
        bv.putUTF8("\u0080"); // 2-byte UTF8 encoding

        byte[] d = bv.data;
        assertEquals(5, bv.length);
        assertArrayEquals(
                new byte[] { 0x7F, 0x00, 0x02, (byte) 0xC2, (byte) 0x80 },
                new byte[] { d[0], d[1], d[2], d[3], d[4] }
        );
    }
}