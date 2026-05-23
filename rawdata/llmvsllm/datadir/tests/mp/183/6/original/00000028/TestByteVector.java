import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector();
        bv.putUTF8("\u20AC"); // U+20AC => E2 82 AC

        assertEquals(5, bv.length);

        byte[] expected = new byte[] { 0x00, 0x03, (byte) 0xE2, (byte) 0x82, (byte) 0xAC };
        byte[] actual = new byte[] { bv.data[0], bv.data[1], bv.data[2], bv.data[3], bv.data[4] };
        assertArrayEquals(expected, actual);
    }
}