import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector();
        bv.putUTF8("\u00A2"); // U+00A2 => C2 A2

        assertEquals(4, bv.length);
        assertArrayEquals(new byte[] { 0x00, 0x02, (byte) 0xC2, (byte) 0xA2 },
                java.util.Arrays.copyOf(bv.data, bv.length));
    }
}