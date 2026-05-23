import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(4);
        v.putByte(0x7F);

        String s = "\u0001\u00A9\u0800";
        v.putUTF8(s);

        byte[] expected = new byte[] {
                (byte) 0x7F,
                0x00, 0x06,
                0x01,
                (byte) 0xC2, (byte) 0xA9,
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80
        };

        assertEquals(expected.length, v.length);
        assertArrayEquals(expected, java.util.Arrays.copyOf(v.data, v.length));
    }
}