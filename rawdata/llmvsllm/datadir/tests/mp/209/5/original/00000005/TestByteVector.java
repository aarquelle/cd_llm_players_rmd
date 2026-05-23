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
        assertEquals(((0 & 0xFF) << 24) | ((3 & 0xFF) << 16) | (((byte) 0xE0 & 0xFF) << 8) | ((byte) 0xA0 & 0xFF),
                ((bv.data[0] & 0xFF) << 24) | ((bv.data[1] & 0xFF) << 16) | ((bv.data[2] & 0xFF) << 8) | (bv.data[3] & 0xFF));
    }
}