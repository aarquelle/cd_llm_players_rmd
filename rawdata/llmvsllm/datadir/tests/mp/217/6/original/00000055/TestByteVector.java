import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(8);
        bv.putLong(0x01020304A0B0C0D0L);

        assertEquals(8, bv.length);
        assertArrayEquals(new byte[] { 1, 2, 3, 4, (byte) 0xA0, (byte) 0xB0, (byte) 0xC0, (byte) 0xD0 }, bv.data);
    }
}