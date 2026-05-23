import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(4);
        v.putUTF8("A\u0000\u0800B");

        byte[] expected = new byte[] {
                0x00, 0x07,
                0x41,
                (byte) 0xC0, (byte) 0x80,
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80,
                0x42
        };

        byte[] actual = new byte[] {
                v.data[0], v.data[1], v.data[2], v.data[3], v.data[4],
                v.data[5], v.data[6], v.data[7], v.data[8]
        };

        assertArrayEquals(expected, actual);
    }
}