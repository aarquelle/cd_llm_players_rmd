import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1); // force enlarge path
        long v = 0x01020304A0B0C0D0L;

        bv.putLong(v);

        assertArrayEquals(
                new byte[] { 0x01, 0x02, 0x03, 0x04, (byte) 0xA0, (byte) 0xB0, (byte) 0xC0, (byte) 0xD0 },
                java.util.Arrays.copyOf(bv.data, bv.length)
        );
        assertEquals(8, bv.length);
    }
}