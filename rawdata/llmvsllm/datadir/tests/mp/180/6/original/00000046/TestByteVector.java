import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putUTF8("\u0080");

        assertArrayEquals(new byte[] {0, 2, (byte) 0xC2, (byte) 0x80}, bv.data);
        assertEquals(4, bv.length);
    }
}