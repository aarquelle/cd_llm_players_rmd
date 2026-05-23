import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        v.putUTF8("Az\u00E9\u0800");

        byte[] expected = new byte[] {
            0x00, 0x07,
            0x41, 0x7A,
            (byte) 0xC3, (byte) 0xA9,
            (byte) 0xE0, (byte) 0xA0, (byte) 0x80
        };

        assertEquals(expected.length, v.length);
        assertArrayEquals(expected, java.util.Arrays.copyOf(v.data, v.length));
    }
}