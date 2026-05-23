import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        v.putByte(0x7F);
        v.putUTF8("\u0800"); // 3-byte UTF-8 encoding, length prefix should be 3
        v.putByteArray(null, 0, 2); // append two zero bytes

        byte[] d = v.data;
        assertArrayEquals(new byte[] { 0x7F, 0x00, 0x03, (byte) 0xE0, (byte) 0xA0, (byte) 0x80, 0x00, 0x00 }, d);

        assertEquals(8, v.length);
    }
}