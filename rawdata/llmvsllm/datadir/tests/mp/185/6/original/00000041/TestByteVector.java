import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        v.putByte(0x7F);
        v.putUTF8("A\u0080\u0800"); // 1-byte + 2-byte + 3-byte UTF8
        v.putByteArray(null, 0, 2); // should append two zero bytes

        assertEquals(1 + 2 + (1 + 2 + 3) + 2, v.length);
        assertArrayEquals(new byte[] {
                (byte) 0x7F,
                0x00, 0x06,
                0x41,
                (byte) 0xC2, (byte) 0x80,
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80,
                0x00, 0x00
        }, java.util.Arrays.copyOf(v.data, v.length));
    }
}