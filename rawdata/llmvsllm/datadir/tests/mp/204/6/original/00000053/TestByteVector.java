import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3); // force enlarge when writing 4 bytes
        bv.putInt(0x89ABCDEF);

        assertEquals(4, bv.length);
        assertArrayEquals(new byte[] {(byte) 0x89, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF},
                new byte[] {bv.data[0], bv.data[1], bv.data[2], bv.data[3]});
    }
}