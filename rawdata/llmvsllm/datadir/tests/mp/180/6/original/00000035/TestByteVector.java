import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(4);
        bv.putByte(0x55);
        bv.putUTF8("A\u0000\u0800B");

        assertArrayEquals(
                new byte[] {
                        (byte) 0x55,
                        0x00, 0x07,
                        0x41,
                        (byte) 0xC0, (byte) 0x80,
                        (byte) 0xE0, (byte) 0xA0, (byte) 0x80,
                        0x42
                },
                java.util.Arrays.copyOf(bv.data, bv.length)
        );
        assertEquals(11, bv.length);
    }
}