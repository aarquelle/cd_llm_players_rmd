import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(3);
        v.putByte(0x55);
        v.putUTF8("\u0080");

        assertEquals(5, v.length);
        assertArrayEquals(new byte[] { 0x55, 0x00, 0x02, (byte) 0xC2, (byte) 0x80 }, v.data);
    }
}