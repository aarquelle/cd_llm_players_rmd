import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(4);
        bv.putUTF8("A\u0800"); // 'A' (1 byte) + U+0800 (3 bytes) => 4 bytes

        assertEquals(6, bv.length); // 2-byte length header + 4 data bytes
        assertArrayEquals(new byte[] {0, 4, 0x41, (byte) 0xE0, (byte) 0xA0, (byte) 0x80},
                new byte[] {bv.data[0], bv.data[1], bv.data[2], bv.data[3], bv.data[4], bv.data[5]});
    }
}