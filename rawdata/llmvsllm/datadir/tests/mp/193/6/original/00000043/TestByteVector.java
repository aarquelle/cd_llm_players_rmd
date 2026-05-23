import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(3);
        v.putByte(0x55);
        v.putUTF8("A\u0800");

        assertEquals(1 + 2 + 4, v.length);

        int idx = 1;
        assertArrayEquals(new byte[] { 0x00, 0x04, 0x41, (byte) 0xE0, (byte) 0xA0, (byte) 0x80 },
                new byte[] { v.data[idx], v.data[idx + 1], v.data[idx + 2], v.data[idx + 3], v.data[idx + 4], v.data[idx + 5] });
    }
}