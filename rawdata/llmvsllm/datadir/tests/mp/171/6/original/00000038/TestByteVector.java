import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.putByte(0x7A);
        v.putShort(0xBEEF);

        assertEquals(3, v.length);
        assertArrayEquals(new byte[]{(byte) 0x7A, (byte) 0xBE, (byte) 0xEF},
                new byte[]{v.data[0], v.data[1], v.data[2]});
    }
}