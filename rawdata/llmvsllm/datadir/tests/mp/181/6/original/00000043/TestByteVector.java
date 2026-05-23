import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);

        String s = "A\u0800"; // 'A' -> 1 byte, U+0800 -> 3 bytes in UTF-8
        v.putUTF8(s);

        assertEquals(2 + 4, v.length);

        byte[] d = v.data;
        assertArrayEquals(
                new byte[] { 0, 4, 0x41, (byte) 0xE0, (byte) 0xA0, (byte) 0x80 },
                new byte[] { d[0], d[1], d[2], d[3], d[4], d[5] }
        );
    }
}