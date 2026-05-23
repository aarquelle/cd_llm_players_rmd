import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3);
        bv.putUTF8("A\u0800B"); // 'A' (1 byte), '\u0800' (3 bytes), 'B' (1 byte) => 5 bytes

        assertEquals(7, bv.length); // 2 length bytes + 5 UTF8 bytes

        byte[] d = bv.data;
        assertArrayEquals(new byte[] {0, 5, 0x41, (byte) 0xE0, (byte) 0xA0, (byte) 0x80, 0x42},
                new byte[] {d[0], d[1], d[2], d[3], d[4], d[5], d[6]});
    }
}