import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(4);
        bv.putUTF8("A\u0000\u0800B"); // A (1 byte), \u0000 (2 bytes), \u0800 (3 bytes), B (1 byte) => 7 bytes

        assertEquals(9, bv.length); // 2 length bytes + 7 payload bytes
        assertArrayEquals(new byte[] {0, 7, 65, (byte) 0xC0, (byte) 0x80, (byte) 0xE0, (byte) 0xA0, (byte) 0x80, 66},
                java.util.Arrays.copyOf(bv.data, bv.length));
    }
}