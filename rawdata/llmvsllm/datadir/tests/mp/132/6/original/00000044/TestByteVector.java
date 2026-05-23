import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                String s = "\u0000\u00A9\u0800A"; // NUL (2 bytes), © (2 bytes), U+0800 (3 bytes), 'A' (1 byte)
        ByteVector bv = new ByteVector(1);
        bv.putUTF8(s);

        int expectedUtf8Len = s.getBytes(StandardCharsets.UTF_8).length;
        assertEquals(2 + expectedUtf8Len + 1, bv.length); // +1 from the null byte array write below

        bv.putByteArray(null, 0, 1);
        assertArrayEquals(new byte[] {0, 8, (byte) 0xC0, (byte) 0x80, (byte) 0xC2, (byte) 0xA9, (byte) 0xE0, (byte) 0xA0, (byte) 0x80, 0x41, 0}, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}