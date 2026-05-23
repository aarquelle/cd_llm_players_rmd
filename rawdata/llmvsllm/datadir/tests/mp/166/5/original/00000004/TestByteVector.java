import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(4);
        v.putInt(0x11223344); // fills initial array exactly; length=4
        v.putByte(0x55);      // triggers enlarge, should copy only first 4 bytes

        assertEquals(5, v.length);
        assertEquals((byte) 0, v.data[4]);
    }
}