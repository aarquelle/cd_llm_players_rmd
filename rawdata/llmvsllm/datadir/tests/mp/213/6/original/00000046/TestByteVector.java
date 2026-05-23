import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(3);
        v.put12(0xA5, 0x1234);
        v.put12(0x5A, 0xBEEF);

        assertEquals(6, v.length);
        assertArrayEquals(new byte[] {(byte) 0xA5, 0x12, 0x34, 0x5A, (byte) 0xBE, (byte) 0xEF},
                java.util.Arrays.copyOf(v.data, v.length));
    }
}