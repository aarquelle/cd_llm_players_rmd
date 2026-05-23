import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                String s = "A\u0080\u0800"; // 1 + 2 + 3 bytes in modified UTF-8 (no NUL)
        ByteVector bv = new ByteVector(2);
        bv.putUTF8(s);

        byte[] expected = new byte[] {
                0x00, 0x06,
                0x41,
                (byte) 0xC2, (byte) 0x80,
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80
        };

        assertAll(
                () -> assertEquals(expected.length, bv.length),
                () -> assertArrayEquals(expected, java.util.Arrays.copyOf(bv.data, bv.length))
        );
    }
}