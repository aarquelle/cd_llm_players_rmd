import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.putUTF8("\u00E9"); // 'é' -> UTF-8: C3 A9, length prefix should be 0x0002

        assertArrayEquals(new byte[] { 0x00, 0x02, (byte) 0xC3, (byte) 0xA9 }, v.data);
        assertEquals(4, v.length);
    }
}