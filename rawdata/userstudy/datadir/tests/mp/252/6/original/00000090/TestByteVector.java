import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.putUTF8("A\u07FF\u0800");

        assertEquals(2 + 1 + 2 + 3, v.length);

        byte[] expected = new byte[] {
            0x00, 0x06,
            0x41,
            (byte) 0xDF, (byte) 0xBF,
            (byte) 0xE0, (byte) 0xA0, (byte) 0x80
        };
        assertArrayEquals(expected, java.util.Arrays.copyOf(v.data, v.length));
    }
}