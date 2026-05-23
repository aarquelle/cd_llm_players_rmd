import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.putUTF8("A\u0080\u0800"); // 'A' -> 1 byte, U+0080 -> 2 bytes, U+0800 -> 3 bytes => 6 bytes payload

        assertEquals(8, v.length); // 2-byte length header + 6 bytes data
        assertArrayEquals(new byte[] {0, 6, 0x41, (byte) 0xC2, (byte) 0x80, (byte) 0xE0, (byte) 0xA0, (byte) 0x80},
                java.util.Arrays.copyOf(v.data, v.length));
    }
}