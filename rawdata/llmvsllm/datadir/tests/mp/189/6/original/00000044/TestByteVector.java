import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2); // force enlarge in putUTF8
        bv.putUTF8("\u0800A"); // 0x0800 -> 3 bytes, 'A' -> 1 byte; total UTF bytes = 4

        byte[] d = bv.data;
        assertArrayEquals(new byte[] { 0, 4, (byte) 0xE0, (byte) 0xA0, (byte) 0x80, 0x41 },
                new byte[] { d[0], d[1], d[2], d[3], d[4], d[5] });
        assertEquals(6, bv.length);
    }
}