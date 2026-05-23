import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.put12(0xAB, 0x1234);

        assertEquals(3, v.length);
        assertArrayEquals(
                new byte[]{(byte) 0xAB, (byte) 0x12, (byte) 0x34},
                new byte[]{v.data[0], v.data[1], v.data[2]}
        );
    }
}