import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        v.putByte(0x7F); // force length=1 to exercise overwrite of length bytes inside putUTF8
        v.putUTF8("A\u0000\u0800"); // 'A' (1 byte), NUL (2 bytes in this impl), U+0800 (3 bytes) => 6 bytes payload

        assertArrayEquals(new byte[] {
                0x7F,
                0x00, 0x06,
                0x41,
                (byte) 0xC0, (byte) 0x80,
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80
        }, java.util.Arrays.copyOf(v.data, v.length));
        assertEquals(10, v.length);
    }
}