import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1); // force enlarge path
        String s = "A\u0080\u0800"; // ASCII (1 byte), 2-byte, 3-byte => total 6 bytes

        bv.putUTF8(s);

        assertEquals(8, bv.length);
        assertArrayEquals(new byte[] { 0, 6, 0x41, (byte) 0xC2, (byte) 0x80, (byte) 0xE0, (byte) 0xA0, (byte) 0x80 },
                Arrays.copyOf(bv.data, bv.length));
    }
}