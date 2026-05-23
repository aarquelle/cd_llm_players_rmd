import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        v.putByte(0x12).putByte(0xA5);

        assertArrayEquals(new byte[] { 0x12, (byte) 0xA5 }, java.util.Arrays.copyOf(v.data, v.length));
        assertEquals(2, v.length);
    }
}