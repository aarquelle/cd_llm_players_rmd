import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putUTF8("A\u0000\u0080\u0800");

        byte[] expected = new byte[] {
                0x00, 0x08,
                0x41,
                (byte) 0xC0, (byte) 0x80,
                (byte) 0xC2, (byte) 0x80,
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80
        };

        byte[] actual = new byte[] {
                bv.data[0], bv.data[1], bv.data[2], bv.data[3], bv.data[4],
                bv.data[5], bv.data[6], bv.data[7], bv.data[8], bv.data[9]
        };

        assertArrayEquals(expected, actual);
    }
}