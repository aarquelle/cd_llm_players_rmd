import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        v.length = 1;
        v.data[0] = 7;

        v.putByte(0xAB);

        assertArrayEquals(new byte[] { 7, (byte) 0xAB }, new byte[] { v.data[0], v.data[1] });
        assertEquals(2, v.length);
    }
}