import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector();
        bv.putUTF8("ABC");
        assertEquals(5, bv.length);
        assertArrayEquals(new byte[] { 0x00, 0x03, 0x41, 0x42, 0x43 }, Arrays.copyOf(bv.data, bv.length));
    }
}