import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);

        v.putUTF8("a\u0800b"); // 'a' (1 byte) + U+0800 (3 bytes) + 'b' (1 byte) => 5 bytes, length prefix = 5
        v.putByteArray(null, 0, 3); // append 3 zero bytes to force additional length changes

        byte[] d = v.data;
        assertArrayEquals(new byte[] {0, 5, 97, (byte) 0xE0, (byte) 0xA0, (byte) 0x80, 98, 0, 0, 0},
                new byte[] {d[0], d[1], d[2], d[3], d[4], d[5], d[6], d[7], d[8], d[9]});
        assertEquals(10, v.length);
    }
}