import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1); // force enlarge paths
        bv.putUTF8("A\u00A2\u0800");

        assertArrayEquals(
                new byte[] { 0, 6, 0x41, (byte) 0xC2, (byte) 0xA2, (byte) 0xE0, (byte) 0xA0, (byte) 0x80 },
                java.util.Arrays.copyOf(bv.data, bv.length)
        );
    }
}