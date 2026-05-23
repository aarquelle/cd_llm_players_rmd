import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3);
        int value = 0x800102FF;

        bv.putInt(value);

        assertArrayEquals(new byte[] { (byte) 0x80, 0x01, 0x02, (byte) 0xFF }, java.util.Arrays.copyOf(bv.data, 4));
        assertEquals(4, bv.length);
    }
}