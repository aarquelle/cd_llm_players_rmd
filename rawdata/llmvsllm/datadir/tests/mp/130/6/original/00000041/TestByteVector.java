import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                String s = "A\u0000\u0080\u07FF\u0800";

        ByteVector bv = new ByteVector(2);
        bv.putByte(0x55).putUTF8(s).putInt(0x01020304);

        byte[] expected = new byte[] {
            // prefix byte
            0x55,
            // UTF length: 1 + 2 + 2 + 2 + 3 = 10
            0x00, 0x0A,
            // 'A'
            0x41,
            // '\u0000' => 0xC0 0x80
            (byte) 0xC0, (byte) 0x80,
            // '\u0080' => 0xC2 0x80
            (byte) 0xC2, (byte) 0x80,
            // '\u07FF' => 0xDF 0xBF
            (byte) 0xDF, (byte) 0xBF,
            // '\u0800' => 0xE0 0xA0 0x80
            (byte) 0xE0, (byte) 0xA0, (byte) 0x80,
            // int 0x01020304
            0x01, 0x02, 0x03, 0x04
        };

        assertArrayEquals(expected, bv.data);
    }
}