import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.putByte(0x55);
        v.put11(0xA1, 0xB2);

        assertArrayEquals(new byte[] { (byte) 0x55, (byte) 0xA1, (byte) 0xB2 }, java.util.Arrays.copyOf(v.data, v.length));
        assertEquals(3, v.length);
    }
}