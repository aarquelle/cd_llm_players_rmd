import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putUTF8("\u0001\u0800");

        assertEquals(6, bv.length); // 2 (len) + 1 + 3
        assertArrayEquals(new byte[] {0, 4, 0x01, (byte) 0xE0, (byte) 0xA0, (byte) 0x80}, bv.data);
    }
}