import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector();
        bv.putUTF8("\u0000A");

        assertEquals(5, bv.length);
        assertArrayEquals(new byte[] {0, 3, (byte) 0xC0, (byte) 0x80, 0x41}, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}