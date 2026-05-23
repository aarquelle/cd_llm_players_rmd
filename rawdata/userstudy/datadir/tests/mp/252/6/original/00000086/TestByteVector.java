import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.putUTF8("\u0080"); // U+0080 should encode as 2 bytes: C2 80

        assertEquals(4, v.length); // 2-byte length prefix + 2 bytes payload
        assertArrayEquals(new byte[] { 0, 2, (byte) 0xC2, (byte) 0x80 }, new byte[] { v.data[0], v.data[1], v.data[2], v.data[3] });
    }
}