import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(3); // force enlarge path
        v.putByte(0x55).putInt(0x01020304);

        assertArrayEquals(new byte[] { (byte) 0x55, 0x01, 0x02, 0x03, 0x04 }, v.data);
        assertEquals(5, v.length);
    }
}