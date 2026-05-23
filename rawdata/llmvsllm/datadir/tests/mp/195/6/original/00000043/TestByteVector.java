import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(4);
        v.putByte(0x55);
        v.putUTF8("A\u07FFB");

        assertEquals(1 + 2 + 4, v.length);

        byte[] d = v.data;
        int idx = 1;
        int utfLen = ((d[idx] & 0xFF) << 8) | (d[idx + 1] & 0xFF);
        assertEquals(4, utfLen);
    }
}