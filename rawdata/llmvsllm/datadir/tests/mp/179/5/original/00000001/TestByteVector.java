import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector();
        bv.putUTF8("\u0080"); // requires 2-byte UTF-8 encoding: C2 80

        assertEquals(4, bv.length); // 2 length bytes + 2 utf8 bytes
        assertEquals((byte) 0xC2, bv.data[2]);
    }
}