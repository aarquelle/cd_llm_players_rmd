import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(4);
        bv.putByte(0x55).putUTF8("A\u0800"); // 'A' (1 byte) + U+0800 (3 bytes) => 4 bytes payload

        assertArrayEquals(new byte[] { 0x55, 0x00, 0x04, 0x41, (byte) 0xE0, (byte) 0xA0, (byte) 0x80 }, java.util.Arrays.copyOf(bv.data, bv.length));
        assertEquals(7, bv.length);
    }
}