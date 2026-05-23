import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2); // force enlarge path
        String s = "\u0080"; // 2-byte UTF-8 encoding
        bv.putUTF8(s);

        assertEquals(4, bv.length); // 2 bytes length header + 2 bytes payload
        assertArrayEquals(new byte[] {0x00, 0x02, (byte) 0xC2, (byte) 0x80}, new byte[] {bv.data[0], bv.data[1], bv.data[2], bv.data[3]});
    }
}