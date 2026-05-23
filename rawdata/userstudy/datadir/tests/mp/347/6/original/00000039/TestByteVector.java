import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.putByte(0x7F);
        v.putByteArray(null, 0, 3);

        assertEquals(4, v.length);

        v.putByte(0x55);
        assertArrayEquals(new byte[] { 0x7F, 0x00, 0x00, 0x00, 0x55 }, v.data);
    }
}