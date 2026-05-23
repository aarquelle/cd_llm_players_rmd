import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);
        bv.putByte(0x7F).putShort(0x1234);

        assertArrayEquals(new byte[] { 0x7F, 0x12, 0x34 }, new byte[] { bv.data[0], bv.data[1], bv.data[2] });
        assertEquals(3, bv.length);
    }
}