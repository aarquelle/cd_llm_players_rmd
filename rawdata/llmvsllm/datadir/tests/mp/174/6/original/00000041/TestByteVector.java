import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(0);
        String s = "A\u00E9\u0800"; // bytes: 'A'(1) + 'é'(2) + U+0800(3) => 6
        bv.putUTF8(s);

        byte[] expected = new byte[] {0, 6, 0x41, (byte) 0xC3, (byte) 0xA9, (byte) 0xE0, (byte) 0xA0, (byte) 0x80};
        assertEquals(expected.length, bv.length);
        assertArrayEquals(expected, Arrays.copyOf(bv.data, bv.length));
    }
}