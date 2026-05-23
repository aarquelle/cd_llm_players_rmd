import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3);
        bv.putByte(0x7F);

        String s = "\u0080\u07FF\u0800"; // 2 + 2 + 3 = 7 bytes in modified UTF-8 encoding here
        bv.putUTF8(s);

        assertArrayEquals(new byte[] {
                0x7F,
                0x00, 0x07,
                (byte) 0xC2, (byte) 0x80,
                (byte) 0xDF, (byte) 0xBF,
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80
        }, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}