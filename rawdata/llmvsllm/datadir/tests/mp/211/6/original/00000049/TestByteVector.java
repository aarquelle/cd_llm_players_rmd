import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(4);
        bv.putByte(0x7F).putInt(0x80FF0102);

        assertArrayEquals(new byte[] { 0x7F, (byte) 0x80, (byte) 0xFF, 0x01, 0x02 }, java.util.Arrays.copyOf(bv.data, bv.length));
        assertEquals(5, bv.length);
    }
}