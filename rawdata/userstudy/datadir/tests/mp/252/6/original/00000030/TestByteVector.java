import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putUTF8("\u0800A");

        assertEquals(6, bv.length);
        assertArrayEquals(new byte[] {0, 4, (byte) 0xE0, (byte) 0xA0, (byte) 0x80, 0x41}, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}