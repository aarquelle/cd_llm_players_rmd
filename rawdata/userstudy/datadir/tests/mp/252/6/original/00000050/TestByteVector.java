import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.putUTF8("A\u0080\u0800");

        assertEquals("000641C280E0A080", hex8(v.data[0]) + hex8(v.data[1]) + hex8(v.data[2]) + hex8(v.data[3]) + hex8(v.data[4]) + hex8(v.data[5]) + hex8(v.data[6]) + hex8(v.data[7]));
        assertSame(v, v.putInt(0x01020304));
        int v = b & 0xFF;
        return "" + hex[v >>> 4] + hex[v & 0x0F];
    }
}