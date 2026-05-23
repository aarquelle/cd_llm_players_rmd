import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);
        String s = "A\u0000\u0080\u07FF\u0800";

        bv.putUTF8(s);

        int headerLen = ((bv.data[0] & 0xFF) << 8) | (bv.data[1] & 0xFF);
        assertEquals(10, headerLen);

        byte[] actual = java.util.Arrays.copyOfRange(bv.data, 2, 2 + headerLen);
        assertArrayEquals(new byte[] { 0x41, (byte) 0xC0, (byte) 0x80, (byte) 0xC2, (byte) 0x80, (byte) 0xDF, (byte) 0xBF, (byte) 0xE0, (byte) 0xA0, (byte) 0x80 }, actual);
    }
}