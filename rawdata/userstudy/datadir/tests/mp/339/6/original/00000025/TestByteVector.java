import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        v.putByte(0x7F)
         .putUTF8("A\u0080\u0800")
         .putByteArray(null, 0, 2);

        byte[] expected = new byte[] {
            0x7F,
            0x00, 0x06,
            0x41,
            (byte) 0xC2, (byte) 0x80,
            (byte) 0xE0, (byte) 0xA0, (byte) 0x80,
            0x00, 0x00
        };

        assertEquals(expected.length, v.length);
        assertArrayEquals(expected, Arrays.copyOf(v.data, v.length));
    }
}