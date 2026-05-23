import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector();
        bv.putUTF8("\u0800"); // requires 3-byte UTF-8 encoding (E0 A0 80), length prefix should be 0x0003

        byte[] d = bv.data;
        assertEquals(5, bv.length);
        assertEquals((byte) 0xE0, d[2]);
    }
}