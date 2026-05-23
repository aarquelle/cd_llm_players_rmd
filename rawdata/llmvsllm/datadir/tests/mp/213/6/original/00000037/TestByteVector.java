import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        v.putByte(0x7F);

        v.putUTF8("A\u0000\u0800"); // 1-byte + 2-byte + 3-byte => 6 bytes payload, length prefix=6

        v.putLong(0x0102030405060708L);

        byte[] d = v.data;
        int start = 1;

        assertArrayEquals(
                new byte[] {
                        0x7F,
                        0x00, 0x06,
                        0x41,
                        (byte) 0xC0, (byte) 0x80,
                        (byte) 0xE0, (byte) 0xA0, (byte) 0x80,
                        0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08
                },
                new byte[] {
                        d[start - 1],
                        d[start], d[start + 1],
                        d[start + 2],
                        d[start + 3], d[start + 4],
                        d[start + 5], d[start + 6], d[start + 7],
                        d[start + 8], d[start + 9], d[start + 10], d[start + 11],
                        d[start + 12], d[start + 13], d[start + 14], d[start + 15]
                }
        );
        assertEquals(1 + 2 + 6 + 8, v.length);
    }
}