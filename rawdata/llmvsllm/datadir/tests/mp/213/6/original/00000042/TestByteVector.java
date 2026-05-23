import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.putUTF8("A\u0800"); // 'A' (1 byte) + U+0800 (3 bytes) => 4 bytes payload

        assertEquals(6, v.length); // 2-byte length prefix + 4 bytes encoded
        assertArrayEquals(new byte[] { 0, 4, 0x41, (byte) 0xE0, (byte) 0xA0, (byte) 0x80 }, v.data);
    }
}