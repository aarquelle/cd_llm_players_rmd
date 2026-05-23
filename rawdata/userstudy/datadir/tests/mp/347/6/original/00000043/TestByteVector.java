import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2); // force enlarge when putting 3 bytes
        bv.put12(0xAB, 0xCDEF);

        byte[] expectedPrefix = new byte[] { (byte) 0xAB, (byte) 0xCD, (byte) 0xEF };
        assertArrayEquals(expectedPrefix, Arrays.copyOf(bv.data, 3));
        assertEquals(3, bv.length);
    }
}