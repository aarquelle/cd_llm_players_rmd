import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(4);
        v.putInt(0x12345678);

        assertArrayEquals(new byte[] { 0x12, 0x34, 0x56, 0x78 }, v.data);
        assertEquals(4, v.length);
    }
}