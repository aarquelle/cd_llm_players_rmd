import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.putByte(0x11).putByte(0x22);
        v.putByteArray(null, 0, 5); // forces enlarge(5): required=7, double=4 => new length must be 7

        assertEquals(7, v.data.length);
        assertEquals((0x11 << 8) | 0x22, ((v.data[0] & 0xFF) << 8) | (v.data[1] & 0xFF));
    }
}