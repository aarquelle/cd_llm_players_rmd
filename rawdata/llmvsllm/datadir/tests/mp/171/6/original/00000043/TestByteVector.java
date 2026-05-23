import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        v.data[0] = 0x55; // sentinel to detect wrong write index
        v.putByte(0x01).putByte(0x7F);

        assertArrayEquals(new byte[] { 0x01, 0x7F }, new byte[] { v.data[0], v.data[1] });
        assertEquals(2, v.length);
    }
}