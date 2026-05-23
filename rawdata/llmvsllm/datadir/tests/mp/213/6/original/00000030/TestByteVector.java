import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(4); // force enlarge paths
        bv.putUTF8("A\u07FF\u0800"); // 1 + 2 + 3 = 6 bytes

        assertEquals(8, bv.length); // 2 length bytes + 6 payload
        assertArrayEquals(new byte[] {0, 6, 0x41, (byte) 0xDF, (byte) 0xBF, (byte) 0xE0, (byte) 0xA0, (byte) 0x80},
                java.util.Arrays.copyOf(bv.data, bv.length));
    }
}