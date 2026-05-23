import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector();
        bv.putUTF8("\u0800"); // U+0800 should encode as E0 A0 80 in modified UTF-8

        assertEquals(5, bv.length);
        assertArrayEquals(new byte[] {0, 3, (byte) 0xE0, (byte) 0xA0, (byte) 0x80}, bv.data);
    }
}