import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.putUTF8("\u0080"); // requires 2-byte UTF-8 encoding

        assertEquals(4, v.length); // 2 bytes length prefix + 2 bytes payload
        assertArrayEquals(new byte[] {0, 2, (byte) 0xC2, (byte) 0x80}, java.util.Arrays.copyOf(v.data, v.length));
    }
}