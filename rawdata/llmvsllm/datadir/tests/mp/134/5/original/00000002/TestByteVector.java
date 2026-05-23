import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector();
        bv.putUTF8("\u007F"); // DEL, should be encoded as single byte in modified UTF-8
        assertEquals(3, bv.length);
        assertEquals((byte) 0x7F, bv.data[2]);
    }
}