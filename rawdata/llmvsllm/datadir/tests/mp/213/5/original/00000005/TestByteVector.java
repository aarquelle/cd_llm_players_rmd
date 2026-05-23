import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(4);
        v.putUTF8("é"); // U+00E9, should be 2 bytes in modified UTF-8

        assertEquals(4, v.length);
        assertArrayEquals(new byte[] {0, 2, (byte) 0xC3, (byte) 0xA9}, new byte[] {v.data[0], v.data[1], v.data[2], v.data[3]});
    }
}