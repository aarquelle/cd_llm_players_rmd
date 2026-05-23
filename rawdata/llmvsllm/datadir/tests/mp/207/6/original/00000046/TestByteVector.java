import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.put11(0x11, 0x22);
        v.put11(0x33, 0x44); // forces enlarge

        assertArrayEquals(new byte[] { 0x11, 0x22, 0x33, 0x44 }, java.util.Arrays.copyOf(v.data, v.length));
        assertEquals(4, v.length);
    }
}