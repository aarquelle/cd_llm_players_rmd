import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putUTF8("A\u00A2\u20AC"); // UTF-8 bytes: 41 C2 A2 E2 82 AC, length = 6

        assertArrayEquals(
                new byte[] {0, 6, 0x41, (byte) 0xC2, (byte) 0xA2, (byte) 0xE2, (byte) 0x82, (byte) 0xAC},
                bv.data
        );
        assertEquals(8, bv.length);
    }
}