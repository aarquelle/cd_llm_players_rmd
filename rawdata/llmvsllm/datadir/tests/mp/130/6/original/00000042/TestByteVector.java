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

        assertEquals(17, bv.length);

        assertArrayEquals(new byte[] {
            0x55,
            0x00, 0x0A,
            0x41,
            (byte) 0xC0, (byte) 0x80,
            (byte) 0xC2, (byte) 0x80,
            (byte) 0xDF, (byte) 0xBF,
            (byte) 0xE0, (byte) 0xA0, (byte) 0x80,
            0x01, 0x02, 0x03, 0x04
        }, new byte[] {
            bv.data[0],  bv.data[1],  bv.data[2],  bv.data[3],  bv.data[4],
            bv.data[5],  bv.data[6],  bv.data[7],  bv.data[8],  bv.data[9],
            bv.data[10], bv.data[11], bv.data[12], bv.data[13], bv.data[14],
            bv.data[15], bv.data[16]
        });
    }
}