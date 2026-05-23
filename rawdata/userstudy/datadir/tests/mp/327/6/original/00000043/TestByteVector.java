import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);
        bv.putByte(0x12).putByte(0xFE);

        assertArrayEquals(new byte[] { (byte) 0x12, (byte) 0xFE }, java.util.Arrays.copyOf(bv.data, bv.length));
        assertEquals(2, bv.length);
    }
}