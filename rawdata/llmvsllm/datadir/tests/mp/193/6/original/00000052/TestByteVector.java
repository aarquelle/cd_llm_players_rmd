import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putByte(0x7F); // force non-zero starting offset to exercise header rewrite at data[length]
        bv.putUTF8("A\u0800"); // 'A' (1 byte) + U+0800 (3 bytes) => byteLength 4, charLength 2

        assertEquals(1 + 2 + 4, bv.length);
        assertArrayEquals(new byte[] { 0x7F, 0x00, 0x04, 0x41, (byte) 0xE0, (byte) 0xA0, (byte) 0x80 },
                java.util.Arrays.copyOf(bv.data, bv.length));
    }
}