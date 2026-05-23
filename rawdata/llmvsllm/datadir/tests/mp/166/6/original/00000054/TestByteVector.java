import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.putByte(0x7A);

        v.put12(0xAB, 0xC0DE);

        assertEquals(4, v.length);
        assertArrayEquals(new byte[] { 0x7A, (byte) 0xAB, (byte) 0xC0, (byte) 0xDE }, v.data);
    }
}