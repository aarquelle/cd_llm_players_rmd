import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(4);
        v.putByte(0x55);
        v.putUTF8("A\u00A2\u0800");

        assertEquals(9, v.length);

        int start = 1;
        assertArrayEquals(
                new byte[] {
                        0x55,
                        0x00, 0x06,
                        0x41,
                        (byte) 0xC2, (byte) 0xA2,
                        (byte) 0xE0, (byte) 0xA0, (byte) 0x80
                },
                java.util.Arrays.copyOf(v.data, start + 2 + 6)
        );
    }
}