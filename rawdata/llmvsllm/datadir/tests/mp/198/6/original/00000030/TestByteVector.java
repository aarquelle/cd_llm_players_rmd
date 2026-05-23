import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        v.putByte(0x7F).putByte(0x80);

        assertArrayEquals(new byte[] { (byte) 0x7F, (byte) 0x80 }, java.util.Arrays.copyOf(v.data, v.length));
        assertEquals(2, v.length);
    }
}