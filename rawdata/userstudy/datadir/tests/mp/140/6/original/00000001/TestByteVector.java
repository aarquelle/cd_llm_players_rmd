import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putUTF8("A\u0800");

        assertEquals(6, bv.length);
        assertArrayEquals(new byte[] {0, 4, 65, (byte) 0xE0, (byte) 0xA0, (byte) 0x80},
                new byte[] {bv.data[0], bv.data[1], bv.data[2], bv.data[3], bv.data[4], bv.data[5]});
    }
}