import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putUTF8("\u00A2\u0800"); // '¢' (2 bytes) + U+0800 (3 bytes) => length prefix 5

        assertEquals(7, bv.length);
        assertArrayEquals(new byte[] {0, 5, (byte) 0xC2, (byte) 0xA2, (byte) 0xE0, (byte) 0xA0, (byte) 0x80}, bv.data);
    }
}