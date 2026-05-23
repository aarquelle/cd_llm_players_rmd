import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.putByte(0x11).putByte(0x22);
        v.putByte(0x33);

        assertArrayEquals(new byte[] { 0x11, 0x22, 0x33 }, java.util.Arrays.copyOf(v.data, v.length));
        assertTrue("Expected doubling from 2 -> 4 when enlarge is called with size=1 at length=2", v.data.length >= 4);
    }
}