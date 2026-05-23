import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putUTF8("A\u0800B");

        assertEquals(7, bv.length); // 2 length bytes + (A=1) + (U+0800=3) + (B=1)

        byte[] expected = new byte[] {0, 5, 0x41, (byte) 0xE0, (byte) 0xA0, (byte) 0x80, 0x42};
        assertArrayEquals(expected, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}