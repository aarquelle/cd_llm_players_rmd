import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(4);
        v.putInt(0x01020304);
        v.putByte(0x05); // triggers enlarge; only first 4 bytes should be preserved

        assertArrayEquals(new byte[] {1, 2, 3, 4, 5}, java.util.Arrays.copyOf(v.data, v.length));
    }
}