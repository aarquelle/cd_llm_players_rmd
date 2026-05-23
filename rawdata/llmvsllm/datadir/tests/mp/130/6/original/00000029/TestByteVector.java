import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(4);
        bv.putUTF8("\u0000\u0800");

        assertEquals(7, bv.length);
        assertArrayEquals(new byte[] {0, 5, (byte) 0xC0, (byte) 0x80, (byte) 0xE0, (byte) 0xA0, (byte) 0x80}, bv.data);
    }
}