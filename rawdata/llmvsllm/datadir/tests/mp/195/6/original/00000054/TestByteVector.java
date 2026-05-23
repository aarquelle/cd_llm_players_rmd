import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2); // force enlarge during putUTF8
        v.putUTF8("A\u0800"); // 'A' (1 byte) + U+0800 (3 bytes) => 4 bytes, length field = 4

        assertArrayEquals(new byte[] { 0x00, 0x04, 0x41, (byte) 0xE0, (byte) 0xA0, (byte) 0x80 },
                java.util.Arrays.copyOf(v.data, v.length));
    }
}