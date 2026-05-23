import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putByte(0x7F); // force non-zero start index for UTF header placement
        bv.putUTF8("A\u0080\u0800"); // 1-byte + 2-byte + 3-byte => 6 bytes payload, header should be 0x0006

        byte[] d = bv.data;
        assertArrayEquals(new byte[] {
                0x7F,
                0x00, 0x06,
                0x41,
                (byte) 0xC2, (byte) 0x80,
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80
        }, java.util.Arrays.copyOf(d, bv.length));
        assertEquals(10, bv.length);
    }
}