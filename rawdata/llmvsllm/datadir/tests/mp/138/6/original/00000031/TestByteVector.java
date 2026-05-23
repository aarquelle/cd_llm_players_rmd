import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                byte[] dst = new byte[len];
        copyRec(src, dst, 0, len);
        return dst;
        dst[i] = src[i];
        copyRec(src, dst, i + 1, len);
        ByteVector bv = new ByteVector(1);
        bv.putUTF8("A\u0080\u0800");

        byte[] expected = new byte[] {
                0x00, 0x06,
                0x41,
                (byte) 0xC2, (byte) 0x80,
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80
        };

        assertArrayEquals(expected, prefix(bv.data, bv.length));
    }
}