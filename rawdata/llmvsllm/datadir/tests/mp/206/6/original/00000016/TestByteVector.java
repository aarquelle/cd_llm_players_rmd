import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                String s = "A\u00E9"; // A + 'é'

        ByteVector bv = new ByteVector(2);
        bv.putUTF8(s);

        byte[] actual = java.util.Arrays.copyOf(bv.data, bv.length);
        byte[] expected = new byte[] { 0x00, 0x03, 0x41, (byte) 0xC3, (byte) 0xA9 };

        assertEquals(5, bv.length);
        assertArrayEquals(expected, actual);
    }
}