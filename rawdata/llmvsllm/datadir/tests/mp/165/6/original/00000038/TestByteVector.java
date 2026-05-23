import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(4);
        bv.putByte(0x55).putUTF8("A\u07FF\u0800");

        assertEquals(1 + 2 + 1 + 2 + 3, bv.length);

        byte[] d = bv.data;
        int p = 1;
        assertArrayEquals(new byte[] {
                0x00, 0x06,
                0x41,
                (byte) 0xDF, (byte) 0xBF,
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80
        }, new byte[] { d[p], d[p+1], d[p+2], d[p+3], d[p+4], d[p+5], d[p+6], d[p+7] });
    }
}