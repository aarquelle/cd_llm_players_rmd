import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);

        v.putByte(0x12)
         .putUTF8("A\u0800") // forces general UTF8 path (includes 3-byte encoding)
         .putByteArray(null, 0, 3); // should append 3 zero bytes

        assertEquals(1 + (2 + 4) + 3, v.length);

        byte[] d = v.data;
        int idx = 0;
        assertArrayEquals(new byte[] {
                0x12,
                0x00, 0x04, 0x41, (byte) 0xE0, (byte) 0xA0, (byte) 0x80,
                0x00, 0x00, 0x00
        }, new byte[] { d[idx++], d[idx++], d[idx++], d[idx++], d[idx++], d[idx++], d[idx++], d[idx++], d[idx++], d[idx++] });
    }
}