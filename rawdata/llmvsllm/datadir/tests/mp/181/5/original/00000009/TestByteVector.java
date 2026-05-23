import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector();
        bv.putUTF8("\u0800"); // requires 3-byte UTF-8 encoding

        byte[] d = bv.data;
        assertEquals(5, bv.length);
        assertArrayEquals(new byte[] {0, 3, (byte) 0xE0, (byte) 0xA0, (byte) 0x80},
                new byte[] {d[0], d[1], d[2], d[3], d[4]});
    }
}