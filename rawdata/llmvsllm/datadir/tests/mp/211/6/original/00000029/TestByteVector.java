import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);

        bv.putUTF8("\u0800"); // requires 3-byte UTF8 encoding, forces recomputation and enlargement
        bv.putByteArray(null, 0, 3); // append 3 zero bytes

        assertEquals(8, bv.length); // 2 length bytes + 3 utf8 bytes + 3 null bytes
        assertArrayEquals(new byte[] {0, 3, (byte) 0xE0, (byte) 0xA0, (byte) 0x80, 0, 0, 0},
                java.util.Arrays.copyOf(bv.data, bv.length));
    }
}