import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        String s = "A\u0800\u07FF";
        bv.putUTF8(s);

        byte[] expected = new byte[] {
                0x00, 0x06,
                0x41,
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80,
                (byte) 0xDF, (byte) 0xBF
        };

        assertArrayEquals(expected, java.util.Arrays.copyOf(bv.data, bv.length));
        assertEquals(8, bv.data.length);
    }
}