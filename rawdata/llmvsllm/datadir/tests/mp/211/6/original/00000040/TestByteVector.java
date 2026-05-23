import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putByte(0x7F); // ensure string doesn't start at index 0
        int start = bv.length;

        String s = "A\u0080\u0800"; // 1-byte, 2-byte, 3-byte UTF-8 => total 6 bytes
        bv.putUTF8(s);

        assertEquals("Total length should be previous + 2 length-bytes + UTF-8 bytes", start + 2 + 6, bv.length);
        assertArrayEquals(new byte[] { 0x00, 0x06 }, new byte[] { bv.data[start], bv.data[start + 1] });
    }
}