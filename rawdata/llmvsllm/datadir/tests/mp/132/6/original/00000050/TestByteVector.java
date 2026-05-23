import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                String s = "A\u0000\u00E9\u0800Z";
        ByteVector bv = new ByteVector(1);
        bv.putUTF8(s);

        byte[] actual = bv.data;
        int n = bv.length;

        assertEquals(10, n);
        assertArrayEquals(new byte[] {0, 8, 65, (byte) 0xC0, (byte) 0x80, (byte) 0xC3, (byte) 0xA9, (byte) 0xE0, (byte) 0xA0, (byte) 0x80}, java.util.Arrays.copyOf(actual, n));
    }
}