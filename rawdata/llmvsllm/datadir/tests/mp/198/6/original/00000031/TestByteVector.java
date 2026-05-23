import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3);
        String s = "A" + '\u0800' + "B";

        bv.putUTF8(s);

        assertArrayEquals(new byte[] {0, 5, 0x41, (byte) 0xE0, (byte) 0xA0, (byte) 0x80, 0x42},
                java.util.Arrays.copyOf(bv.data, bv.length));
        assertEquals(7, bv.length);
    }
}