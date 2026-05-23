import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(4);
        bv.putByte(0x7F);
        bv.putUTF8("\u0800"); // 3-byte UTF8 encoding

        assertEquals(6, bv.length); // 1 + 2 + 3
        assertArrayEquals(new byte[] { 0x7F, 0x00, 0x03, (byte) 0xE0, (byte) 0xA0, (byte) 0x80 }, bv.data);
    }
}