import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putUTF8("A\u0800"); // 'A' (1 byte) + U+0800 (3 bytes) => 4 UTF8 bytes, header=0x0004

        assertEquals(6, bv.length); // 2 (header) + 4 (payload)
        assertArrayEquals(new byte[] {0, 4, 65, (byte) 0xE0, (byte) 0xA0, (byte) 0x80}, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}