import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3);
        bv.putByte(0x7F);
        bv.putInt(0x01020304);

        assertArrayEquals(
                ByteBuffer.allocate(5).put((byte) 0x7F).putInt(0x01020304).array(),
                java.util.Arrays.copyOf(bv.data, bv.length)
        );
        assertEquals(5, bv.length);
    }
}