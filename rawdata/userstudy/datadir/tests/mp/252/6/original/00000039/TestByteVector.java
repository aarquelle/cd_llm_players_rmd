import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(3);
        v.putByte(0x7F);
        v.putUTF8("\u0000\u07FF\u0800");

        assertEquals(1 + 2 + 2 + 3 + 3, v.length);

        byte[] d = v.data;
        assertArrayEquals(new byte[] {
                0x7F,
                0x00, 0x08,
                (byte) 0xC0, (byte) 0x80,
                (byte) 0xDF, (byte) 0xBF,
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80
        }, new byte[] { d[0], d[1], d[2], d[3], d[4], d[5], d[6], d[7], d[8], d[9], d[10] });
    }
}