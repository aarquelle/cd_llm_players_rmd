import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3);
        bv.putUTF8("A\u00A2"); // 'A' (1 byte) + '¢' (2 bytes) => byteLength=3, total added=2+3=5

        assertEquals(5, bv.length);
        assertArrayEquals(new byte[] {0, 3, 0x41, (byte) 0xC2, (byte) 0xA2}, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}