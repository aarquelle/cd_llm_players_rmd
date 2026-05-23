import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(3); // force resize path when encoding expands
        v.putUTF8("\u0080\u0800"); // 2 chars -> 2 + 3 bytes = 5 bytes encoded

        byte[] expected = new byte[] {
                0x00, 0x05,             // UTF length (5)
                (byte) 0xC2, (byte) 0x80, // U+0080
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800
        };

        assertEquals(7, v.length);
        assertArrayEquals(expected, java.util.Arrays.copyOf(v.data, v.length));
    }
}