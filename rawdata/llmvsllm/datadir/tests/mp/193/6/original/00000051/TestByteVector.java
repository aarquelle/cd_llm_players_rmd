import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(3); // force enlarge path in putUTF8
        v.putUTF8("A\u0800"); // 'A' (1 byte) + U+0800 (3 bytes) => 4 bytes, header should be 0x00 0x04

        assertArrayEquals(new byte[] {0, 4, 0x41, (byte) 0xE0, (byte) 0xA0, (byte) 0x80},
                java.util.Arrays.copyOf(v.data, v.length));
    }
}