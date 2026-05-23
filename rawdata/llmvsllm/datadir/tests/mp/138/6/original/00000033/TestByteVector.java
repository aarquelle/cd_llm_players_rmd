import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.putByte(0x7F);

        byte[] src = new byte[] { 9, (byte) 0xAA, (byte) 0xBB, (byte) 0xCC, 8 };
        v.putByteArray(src, 1, 3);

        assertEquals(4, v.length);
        assertArrayEquals(new byte[] { 0x7F, (byte) 0xAA, (byte) 0xBB, (byte) 0xCC }, java.util.Arrays.copyOf(v.data, v.length));
    }
}