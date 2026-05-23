import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(4);
        v.putByte(0x55); // prefix to shift indices and exercise internal header rewrite path
        v.putUTF8("A\u0080\u0800"); // 1 + 2 + 3 bytes = 6, should rewrite length header to 0x0006

        assertEquals(9, v.length); // 1 + 2 + 6
        assertArrayEquals(
                new byte[] {
                        (byte) 0x55,
                        0x00, 0x06,
                        0x41,
                        (byte) 0xC2, (byte) 0x80,
                        (byte) 0xE0, (byte) 0xA0, (byte) 0x80
                },
                java.util.Arrays.copyOf(v.data, v.length)
        );
    }
}