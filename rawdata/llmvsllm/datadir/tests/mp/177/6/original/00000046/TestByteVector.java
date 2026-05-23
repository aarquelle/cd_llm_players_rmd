import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.putByte(0x01).putShort(0x0203).putInt(0x04050607);

        assertEquals(1 + 2 + 4, v.length);
        assertArrayEquals(new byte[] { 1, 2, 3, 4, 5, 6, 7 }, Arrays.copyOf(v.data, v.length));
    }
}