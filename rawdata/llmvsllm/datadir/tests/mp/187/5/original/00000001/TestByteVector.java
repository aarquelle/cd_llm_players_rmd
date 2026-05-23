import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(8);
        bv.putUTF8("\u00E9"); // é -> 2-byte UTF-8

        assertEquals(4, bv.length);
        assertEquals((byte) 0xA9, bv.data[3]);
    }
}