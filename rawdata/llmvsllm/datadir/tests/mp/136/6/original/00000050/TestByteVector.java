import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(4);
        v.putByte(0x55);

        String s = "A\u0800B"; // 'A'(1) + '\u0800'(3) + 'B'(1) => 5 bytes
        v.putUTF8(s);

        byte[] expected = new byte[] {
                0x55,
                0x00, 0x05,
                0x41,
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80,
                0x42
        };

        assertArrayEquals(expected, java.util.Arrays.copyOf(v.data, v.length));
    }
}