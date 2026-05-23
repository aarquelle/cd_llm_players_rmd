import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        v.putByte(0x7F);
        v.putUTF8("A\u0000\u07FF\u0800"); // forces non-ASCII path and recalculates byte length
        v.putLong(0x0102030405060708L);

        byte[] d = v.data;
        int idx = 0;

        assertEquals(
                "7F 00 09 41 C0 80 DF BF E0 A0 80 01 02 03 04 05 06 07 08",
                String.format(
                        "%02X %02X %02X %02X %02X %02X %02X %02X %02X %02X %02X %02X %02X %02X %02X %02X %02X %02X %02X",
                        d[idx++] & 0xFF, d[idx++] & 0xFF, d[idx++] & 0xFF, d[idx++] & 0xFF, d[idx++] & 0xFF,
                        d[idx++] & 0xFF, d[idx++] & 0xFF, d[idx++] & 0xFF, d[idx++] & 0xFF, d[idx++] & 0xFF,
                        d[idx++] & 0xFF, d[idx++] & 0xFF, d[idx++] & 0xFF, d[idx++] & 0xFF, d[idx++] & 0xFF,
                        d[idx++] & 0xFF, d[idx++] & 0xFF, d[idx++] & 0xFF, d[idx++] & 0xFF
                )
        );
        assertEquals(19, v.length);
    }
}